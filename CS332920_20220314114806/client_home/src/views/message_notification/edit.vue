<template>
	<div class="diy_edit page_message_notification" id="message_notification_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','buy_goods') || $check_field('add','buy_goods') || $check_field('get','buy_goods')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>求购物品:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_buy_goods" v-model="form['buy_goods']" placeholder="请输入求购物品" v-if="(form['buy_goods'] && $check_field('set','buy_goods')) || (!form['buy_goods'] && $check_field('add','buy_goods'))"  :disabled="disabledObj['buy_goods_isDisabled']"/>
							<span v-else-if="$check_field('get','buy_goods')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','item_type') || $check_field('add','item_type') || $check_field('get','item_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>物品类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_item_type" v-model="form['item_type']" placeholder="请输入物品类型" v-if="(form['item_type'] && $check_field('set','item_type')) || (!form['item_type'] && $check_field('add','item_type'))"  :disabled="disabledObj['item_type_isDisabled']"/>
							<span v-else-if="$check_field('get','item_type')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','buy_specifications') || $check_field('add','buy_specifications') || $check_field('get','buy_specifications')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>求购规格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_buy_specifications" v-model="form['buy_specifications']" placeholder="请输入求购规格" v-if="(form['buy_specifications'] && $check_field('set','buy_specifications')) || (!form['buy_specifications'] && $check_field('add','buy_specifications'))"  :disabled="disabledObj['buy_specifications_isDisabled']"/>
							<span v-else-if="$check_field('get','buy_specifications')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','seller') || $check_field('add','seller') || $check_field('get','seller')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>卖家:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_seller" :disabled="disabledObj['seller_isDisabled']" v-model="form['seller']" v-if="(form['seller'] && $check_field('set','seller')) || (!form['seller'] && $check_field('add','seller'))" >
								<option v-for="o in list_user_seller" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','seller')">{{ form['seller'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','message_time') || $check_field('add','message_time') || $check_field('get','message_time')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>留言时间:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['message_time_isDisabled']" id="form_message_time" v-model="form['message_time']" placeholder="请输入留言时间" v-if="(form['message_time'] && $check_field('set','message_time')) || (!form['message_time'] && $check_field('add','message_time'))" />
							<span v-else-if="$check_field('get','message_time')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','buyer_account') || $check_field('add','buyer_account') || $check_field('get','buyer_account')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>买家账号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_buyer_account" :disabled="disabledObj['buyer_account_isDisabled']" v-model="form['buyer_account']" v-if="(form['buyer_account'] && $check_field('set','buyer_account')) || (!form['buyer_account'] && $check_field('add','buyer_account'))" >
								<option v-for="o in list_user_buyer_account" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','buyer_account')">{{ form['buyer_account'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','message_content') || $check_field('add','message_content') || $check_field('get','message_content')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>留言内容:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_message_content" v-model="form['message_content']" v-if="(form['message_content'] && $check_field('set','message_content')) || (!form['message_content'] && $check_field('add','message_content'))" :disabled="disabledObj['message_content_isDisabled']" />
							<span v-else-if="$check_field('get','message_content')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','reply_content') || $check_field('add','reply_content') || $check_field('get','reply_content')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>回复内容:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_reply_content" v-model="form['reply_content']" v-if="(form['reply_content'] && $check_field('set','reply_content')) || (!form['reply_content'] && $check_field('add','reply_content'))" :disabled="disabledObj['reply_content_isDisabled']" />
							<span v-else-if="$check_field('get','reply_content')">{{ form[''] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/message_notification/get_obj?",
				url_add: "~/api/message_notification/add?",
				url_set: "~/api/message_notification/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"buy_goods": "",
					"item_type": "",
					"buy_specifications": "",
					"seller": 0,
					"message_time": "",
					"buyer_account": 0,
					"message_content": "",
					"reply_content": "",
					"message_notification_id": 0,
				},

				obj: {
					"buy_goods":'', // 求购物品
					"item_type":'', // 物品类型
					"buy_specifications":'', // 求购规格
					"seller": 0, // 卖家
					"message_time": "1970-01-01 00:00:00",
					"buyer_account": 0, // 买家账号
					"message_content":'', // 留言内容
					"reply_content":'', // 回复内容
					"message_notification_id": 0,
				},

				// 表单字段
				form: {
					"buy_goods":'', // 求购物品
					"item_type":'', // 物品类型
					"buy_specifications":'', // 求购规格
					"seller": 0, // 卖家
					"message_time": "1970-01-01 00:00:00",
					"buyer_account": 0, // 买家账号
					"message_content":'', // 留言内容
					"reply_content":'', // 回复内容
					"message_notification_id": 0,

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
				// 用户列表
				list_user_buyer_account: [],

				// ID字段
				field: "message_notification_id",
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
			async get_user_session_seller(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=卖家");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["seller"] = user_id
								_this.disabledObj['seller' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="seller") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
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
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/message_notification/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["message_time"] = this.$toTime(parseInt(this.form["message_time"]),"yyyy-MM-dd")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_seller();
			this.get_list_user_seller();
			this.get_list_user_buyer_account();
		}
	}
</script>

<style>
</style>
