<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','buy_goods') || $check_field('add','buy_goods') || $check_field('set','buy_goods')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="求购物品" prop="buy_goods">
					<el-input id="buy_goods" v-model="form['buy_goods']" placeholder="请输入求购物品"
							  v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','buy_goods')) || (!form['purchase_center_id'] && $check_field('add','buy_goods'))" :disabled="disabledObj['buy_goods_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','buy_goods')">{{form['buy_goods']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品类型" prop="item_type">
					<el-select id="item_type" v-model="form['item_type']"
						v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','item_type')) || (!form['purchase_center_id'] && $check_field('add','item_type'))">
						<el-option v-for="o in list_item_type" :key="o['item_type']" :label="o['item_type']"
							:value="o['item_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_picture') || $check_field('add','item_picture') || $check_field('set','item_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品图片" prop="item_picture">
					<el-upload :disabled="disabledObj['item_picture_isDisabled']" id="item_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_item_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','item_picture')) || (!form['purchase_center_id'] && $check_field('add','item_picture'))">
						<img v-if="form['item_picture']" :src="$fullUrl(form['item_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','item_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['item_picture'])" :preview-src-list="[$fullUrl(form['item_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchasing_price') || $check_field('add','purchasing_price') || $check_field('set','purchasing_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="收购价格" prop="purchasing_price">
					<el-input id="purchasing_price" v-model="form['purchasing_price']" placeholder="请输入收购价格"
							  v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','purchasing_price')) || (!form['purchase_center_id'] && $check_field('add','purchasing_price'))" :disabled="disabledObj['purchasing_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','purchasing_price')">{{form['purchasing_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="求购数量" prop="purchase_quantity">
					<el-input id="purchase_quantity" v-model="form['purchase_quantity']" placeholder="请输入求购数量"
							  v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','purchase_quantity')) || (!form['purchase_center_id'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','purchase_quantity')">{{form['purchase_quantity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','buy_specifications') || $check_field('add','buy_specifications') || $check_field('set','buy_specifications')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="求购规格" prop="buy_specifications">
					<el-input id="buy_specifications" v-model="form['buy_specifications']" placeholder="请输入求购规格"
							  v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','buy_specifications')) || (!form['purchase_center_id'] && $check_field('add','buy_specifications'))" :disabled="disabledObj['buy_specifications_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','buy_specifications')">{{form['buy_specifications']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布时间" prop="release_time">
					<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','release_time')) || (!form['purchase_center_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','buyer_account') || $check_field('add','buyer_account') || $check_field('set','buyer_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="买家账号" prop="buyer_account">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_buyer_account(form['buyer_account']) }}
							<!--<el-input id="business_name" v-model="form['buyer_account']" placeholder="请输入买家账号"-->
							<!--v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','buyer_account')) || (!form['purchase_center_id'] && $check_field('add','buyer_account'))" :disabled="disabledObj['buyer_account_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','buyer_account')">{{form['buyer_account']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['purchase_center_id'] && $check_field('set','buyer_account')) || (!form['purchase_center_id'] && $check_field('add','buyer_account'))" id="buyer_account" v-model="form['buyer_account']" :disabled="disabledObj['buyer_account_isDisabled']">
								<el-option v-for="o in list_user_buyer_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','buyer_account')" id="buyer_account" v-model="form['buyer_account']" :disabled="true">
								<el-option v-for="o in list_user_buyer_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="buyer_account" v-model="form['buyer_account']" :disabled="disabledObj['buyer_account_isDisabled']">
							<el-option v-for="o in list_user_buyer_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
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
				field: "purchase_center_id",
				url_add: "~/api/purchase_center/add?",
				url_set: "~/api/purchase_center/set?",
				url_get_obj: "~/api/purchase_center/get_obj?",
				url_upload: "~/api/purchase_center/upload?",

				query: {
					"purchase_center_id": 0,
				},

				form: {
					"buy_goods":'', // 求购物品
					"item_type":'', // 物品类型
					"item_picture":'', // 物品图片
					"purchasing_price":'', // 收购价格
					"purchase_quantity":'', // 求购数量
					"buy_specifications":'', // 求购规格
					"release_time":'', // 发布时间
					"buyer_account": 0, // 买家账号
					"purchase_center_id": 0, // ID

				},
				disabledObj:{
					"buy_goods_isDisabled": false,
					"item_type_isDisabled": false,
					"item_picture_isDisabled": false,
					"purchasing_price_isDisabled": false,
					"purchase_quantity_isDisabled": false,
					"buy_specifications_isDisabled": false,
					"release_time_isDisabled": false,
					"buyer_account_isDisabled": false,
				},
				//物品类型选项列表
				list_item_type: [],
				// 用户列表
				list_user_buyer_account: [],
				// 用户组
				group_user_buyer_account: "",

			}
		},
		methods: {
			/**
			 * 获取物品类型列表
			 */
			async get_list_item_type() {
				var json = await this.$get("~/api/item_information/get_list?");
				if(json.result && json.result.list){
					this.list_item_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传物品图片
			 * @param {Object} param图片参数
			 */
			upload_item_picture(param){
				this.uploadFile(param.file, "item_picture");
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
			/**
			 * 获取买家用户组
			 */
			async get_group_user_buyer_account() {
				this.form["buyer_account"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=买家");
				if(json.result && json.result.obj){
					this.group_user_buyer_account = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_buyer_account(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_buyer_account.source_table+"/get_obj?"
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
									if (arr[i]!=="buyer_account") {
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
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
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
				this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["release_time"]) > 9999){
					this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/purchase_center/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_center/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_center/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_center/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_center/view','get');
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
			this.get_list_item_type();
			this.get_list_user_buyer_account();
			this.get_group_user_buyer_account();
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
