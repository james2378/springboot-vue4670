<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','buy_goods') || $check_field('add','buy_goods') || $check_field('set','buy_goods')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="求购物品" prop="buy_goods">
					<el-input id="buy_goods" v-model="form['buy_goods']" placeholder="请输入求购物品"
							  v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','buy_goods')) || (!form['message_notification_id'] && $check_field('add','buy_goods'))" :disabled="disabledObj['buy_goods_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','buy_goods')">{{form['buy_goods']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品类型" prop="item_type">
					<el-input id="item_type" v-model="form['item_type']" placeholder="请输入物品类型"
							  v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','item_type')) || (!form['message_notification_id'] && $check_field('add','item_type'))" :disabled="disabledObj['item_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','buy_specifications') || $check_field('add','buy_specifications') || $check_field('set','buy_specifications')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="求购规格" prop="buy_specifications">
					<el-input id="buy_specifications" v-model="form['buy_specifications']" placeholder="请输入求购规格"
							  v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','buy_specifications')) || (!form['message_notification_id'] && $check_field('add','buy_specifications'))" :disabled="disabledObj['buy_specifications_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','buy_specifications')">{{form['buy_specifications']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seller') || $check_field('add','seller') || $check_field('set','seller')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="卖家" prop="seller">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_seller(form['seller']) }}
							<!--<el-input id="business_name" v-model="form['seller']" placeholder="请输入卖家"-->
							<!--v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','seller')) || (!form['message_notification_id'] && $check_field('add','seller'))" :disabled="disabledObj['seller_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','seller')">{{form['seller']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','seller')) || (!form['message_notification_id'] && $check_field('add','seller'))" id="seller" v-model="form['seller']" :disabled="disabledObj['seller_isDisabled']">
								<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','seller')" id="seller" v-model="form['seller']" :disabled="true">
								<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="seller" v-model="form['seller']" :disabled="disabledObj['seller_isDisabled']">
							<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','message_time') || $check_field('add','message_time') || $check_field('set','message_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="留言时间" prop="message_time">
					<el-date-picker :disabled="disabledObj['message_time_isDisabled']" v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','message_time')) || (!form['message_notification_id'] && $check_field('add','message_time'))" id="message_time"
						v-model="form['message_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','message_time')">{{form['message_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','buyer_account') || $check_field('add','buyer_account') || $check_field('set','buyer_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="买家账号" prop="buyer_account">
						<el-select v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','buyer_account')) || (!form['message_notification_id'] && $check_field('add','buyer_account'))" id="buyer_account" v-model="form['buyer_account']" :disabled="disabledObj['buyer_account_isDisabled']">
							<el-option v-for="o in list_user_buyer_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','buyer_account')" id="buyer_account" v-model="form['buyer_account']" :disabled="true">
							<el-option v-for="o in list_user_buyer_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','message_content') || $check_field('add','message_content') || $check_field('set','message_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="留言内容" prop="message_content">
					<el-input type="textarea" id="message_content" v-model="form['message_content']" placeholder="请输入留言内容"
						v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','message_content')) || (!form['message_notification_id'] && $check_field('add','message_content'))" :disabled="disabledObj['message_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','message_content')">{{form['message_content']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reply_content') || $check_field('add','reply_content') || $check_field('set','reply_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="回复内容" prop="reply_content">
					<el-input type="textarea" id="reply_content" v-model="form['reply_content']" placeholder="请输入回复内容"
						v-if="user_group === '管理员' || (form['message_notification_id'] && $check_field('set','reply_content')) || (!form['message_notification_id'] && $check_field('add','reply_content'))" :disabled="disabledObj['reply_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reply_content')">{{form['reply_content']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "message_notification_id",
				url_add: "~/api/message_notification/add?",
				url_set: "~/api/message_notification/set?",
				url_get_obj: "~/api/message_notification/get_obj?",
				url_upload: "~/api/message_notification/upload?",

				query: {
					"message_notification_id": 0,
				},

				form: {
					"buy_goods":'', // 求购物品
					"item_type":'', // 物品类型
					"buy_specifications":'', // 求购规格
					"seller": 0, // 卖家
					"message_time":'', // 留言时间
					"buyer_account": 0, // 买家账号
					"message_content":'', // 留言内容
					"reply_content":'', // 回复内容
					"message_notification_id": 0, // ID

				},
				disabledObj:{
					"buy_goods_isDisabled": false,
					"item_type_isDisabled": false,
					"buy_specifications_isDisabled": false,
					"seller_isDisabled": false,
					"message_time_isDisabled": false,
					"buyer_account_isDisabled": false,
					"message_content_isDisabled": false,
					"reply_content_isDisabled": false,
				},
				// 用户列表
				list_user_seller: [],
				// 用户组
				group_user_seller: "",
				// 用户列表
				list_user_buyer_account: [],

			}
		},
		methods: {
			/**
			 * 获取卖家用户列表
			 */
			async get_list_user_seller() {
                // if(this.user_group !== "管理员" && this.form["seller"] === 0) {
                //     this.form["seller"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=卖家");
                if(json.result && json.result.list){
                    this.list_user_seller = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取卖家用户组
			 */
			async get_group_user_seller() {
				this.form["seller"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=卖家");
				if(json.result && json.result.obj){
					this.group_user_seller = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_seller(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_seller.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="seller") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_seller(id){
				var obj = this.list_user_seller.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取买家用户列表
			 */
			async get_list_user_buyer_account() {
                // if(this.user_group !== "管理员" && this.form["buyer_account"] === 0) {
                //     this.form["buyer_account"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=买家");
                if(json.result && json.result.list){
                    this.list_user_buyer_account = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_buyer_account(id){
				var obj = this.list_user_buyer_account.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "卖家":
							if(param["seller"] > 0){
								param["seller"] = this.user.user_id;
							}
							break;
						case "买家账号":
							if(param["buyer_account"] > 0){
								param["buyer_account"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["message_time"] = this.$toTime(parseInt(this.form["message_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["message_time"]) > 9999){
					this.form["message_time"] = this.$toTime(parseInt(this.form["message_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/message_notification/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/message_notification/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/message_notification/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/message_notification/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/message_notification/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_seller();
			this.get_group_user_seller();
			this.get_list_user_buyer_account();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
