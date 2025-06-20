<template>
	<el-form ref="form" :rules="rules" :model="form" status-icon label-width="80px">
		<el-col :xs="24" :sm="12" :lg="8">
			<el-form-item label="商品规格" prop="commodity_specifications">
				<el-input id="commodity_specifications" v-model="form['commodity_specifications']" placeholder="请输入商品规格"
					v-if="user_group == '管理员' || (form['second_hand_goods_id'] && $check_field('set','commodity_specifications') ) || $check_field('add','commodity_specifications')"></el-input>
				<div v-else-if="$check_field('get','commodity_specifications')">{{form['commodity_specifications']}}</div>
			</el-form-item>
		</el-col>
		<el-col :xs="24" :sm="12" :lg="8">
			<el-form-item label="新旧程度" prop="new_and_old_degree">
				<el-input id="new_and_old_degree" v-model="form['new_and_old_degree']" placeholder="请输入新旧程度"
					v-if="user_group == '管理员' || (form['second_hand_goods_id'] && $check_field('set','new_and_old_degree') ) || $check_field('add','new_and_old_degree')"></el-input>
				<div v-else-if="$check_field('get','new_and_old_degree')">{{form['new_and_old_degree']}}</div>
			</el-form-item>
		</el-col>
		
		<el-col :xs="24" :sm="24" :lg="24" style="text-align: center;">
			<el-button type="primary" @click="submit()">提交</el-button>
			<el-button @click="cancel()">取消</el-button>
		</el-col>
		
	</el-form>
</template>

<script>
	import mixin from "../../mixins/component.js";
	
	export default {
		mixins: [mixin],
		props:{
			query: {
				type: Object,
				default: function(){
					return {
						"second_hand_goods_id": 0
					}
				}
			},
			form_goods:{
				type: Object,
				default: function(){
					return {}
				}
			},
			func_check:{
				type: Function,
				default: function(fun){
					console.log("调试输出",fun);
				}
			},
			func_submit:{
				type: Function,
				default: function(fun){
					console.log("调试输出",fun);
				}
			}
		},
		data() {
			return {
				field: "second_hand_goods_id",
				url_add: "~/api/second_hand_goods/add?",
				url_set: "~/api/second_hand_goods/set?",
				url_get_obj: "~/api/second_hand_goods/get_obj?",
				url_upload: "~/api/second_hand_goods/upload?",
				
				form: {
					"commodity_specifications":'',
					"new_and_old_degree":'',
					"seller": 0,
				},
	
				rules: {
					"commodity_specifications": [ ],
					"new_and_old_degree": [ ],
					"seller": [ ],
				}
	
			}
		},
		methods: {
			/**
			 * 请求列表前
			 * @param {Object} param
			 */
			get_list_before(param) {
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "卖家":
							param["seller"] = this.user.user_id;
							break;
					}
				}
	
				return param;
			},
			
			submit(){
				this.func_check(()=>{
					this.$refs["form"].validate((valid) => {
						if (valid) {
							this.submit();
						} else {
							console.error('error 提交失败!!');
						}
					});
				})
			},
			
			submit_after(){
				var source_id = this.form_goods.source_id;
				if(source_id){
					this.func_submit();
				}else{
					this.$get('~/api/second_hand_goods/get_obj?',this.form,(res)=>{
						if(res.result && res.result.obj){
							this.form_goods.source_id = res.result.obj["second_hand_goods_id"];
							this.func_submit();
						}else{
							console.error(res.error);
						}
						
					})
				}
			}
		},
		created() {
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
