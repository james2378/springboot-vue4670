<template>
	<el-main class="bg">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>

				<el-col :xs="24" :sm="24" :lg="8">
					<el-form-item label="姓名">
						<el-input v-model="query.name"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="14" :lg="8">
					<el-form-item label="关键词">
						<el-input v-model="query.keywords"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="24" :lg="24">
					<el-col :xs="24" :sm="12" :lg="12">
						<el-form-item>
							<el-button type="primary" @click="search()">查询</el-button>
							<el-button @click="reset()">重置</el-button>
						</el-form-item>
					</el-col>

					<el-col :xs="24" :sm="12" :lg="12">
						<el-form-item>
							<el-button v-if="user_group == '管理员' || $check_action('/address/table','del')" class="float-right" type="danger" @click="delInfo()">删除</el-button>
							<router-link v-if="user_group == '管理员' || $check_action('/address/view')" class="el-button float-right el-button--default el-button--primary" to="./view?">添加
							</router-link>
						</el-form-item>
					</el-col>
				</el-col>

			</el-row>
		</el-form>
		<el-table border :data="list" @selection-change="selectionChange" style="width: 100%">

			<el-table-column fixed prop="name" label="姓名" width="120">

			</el-table-column>

			<el-table-column prop="phone" label="手机号码" min-width="120">

			</el-table-column>

			<el-table-column prop="postcode" label="邮编" min-width="100">

			</el-table-column>

			<el-table-column prop="address" label="地址" min-width="300">

			</el-table-column>

			<el-table-column sortable prop="create_time" label="创建时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<el-table-column fixed="right" label="操作" width="55">
				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-circle is-plain el-button--primary" :to="'./view?' + field + '=' + scope.row[field]"
					 size="small">编辑</router-link>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="query.page"
			 :page-sizes="[7, 10, 30, 100]" :page-size="query.size" layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 获取数据地址
				url_get_list: "~/api/address/get_list?like=0",
				url_del: "~/api/address/del?",

				// 字段ID
				field:"address_id",

				// 查询
				query: {
					size: 10,
					page: 1,
					name: "",
					keywords: "",
				},

				// 数据
				list: [],

			}
		},
		methods: {
			deleteRow(index, rows) {
				rows.splice(index, 1);
			},

			table_class({row, column, rowIndex, columnIndex}){
				return "table_class";
			}
		},
		created(){

		}
	}
</script>

<style type="text/css">

</style>
