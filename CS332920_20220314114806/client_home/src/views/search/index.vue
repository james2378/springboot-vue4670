<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 商品搜索结果 -->
			  <list_result_search
				:list="result_goods"
				title="二手商城"
				source_table="goods"
			  ></list_result_search>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="二手物品资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/buyers/list', 'get')"
				:list="result_buyers_buyer_account"
				title="买家买家账号"
				source_table="buyers"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/second_hand_goods/list', 'get')"
				:list="result_second_hand_goods_new_and_old_degree"
				title="二手物品新旧程度"
				source_table="second_hand_goods"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/purchase_center/list', 'get')"
				:list="result_purchase_center_buy_goods"
				title="求购中心求购物品"
				source_table="purchase_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/purchase_center/list', 'get')"
				:list="result_purchase_center_item_type"
				title="求购中心物品类型"
				source_table="purchase_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/message_notification/list', 'get')"
				:list="result_message_notification_buy_goods"
				title="消息通知求购物品"
				source_table="message_notification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/message_notification/list', 'get')"
				:list="result_message_notification_item_type"
				title="消息通知物品类型"
				source_table="message_notification"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_goods": [],
	  "result_article": [],
			"result_buyers_buyer_account":[],
			"result_second_hand_goods_new_and_old_degree":[],
			"result_purchase_center_buy_goods":[],
			"result_purchase_center_item_type":[],
			"result_message_notification_buy_goods":[],
			"result_message_notification_item_type":[],
	};
  },
  methods: {
	/**
	 * 获取二手商城
	 */
	get_goods() {
	  this.$get("~/api/goods/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_goods = json.result.list;
		}
	  });
	},
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取buyer_account
	 */
	get_buyers_buyer_account(){
		this.$get("~/api/buyers/get_list?like=0", { page: 1, size: 10, "buyer_account": this.query.word }, (json) => {
		  if (json.result) {
			var result_buyers_buyer_account = json.result.list;
			result_buyers_buyer_account.map(o => o.title = o['buyer_account'])
	  			this.result_buyers_buyer_account = result_buyers_buyer_account
		 	}
		});
	},
	/**
	 * 获取new_and_old_degree
	 */
	get_second_hand_goods_new_and_old_degree(){
		this.$get("~/api/second_hand_goods/get_list?like=0", { page: 1, size: 10, "new_and_old_degree": this.query.word }, (json) => {
		  if (json.result) {
			var result_second_hand_goods_new_and_old_degree = json.result.list;
			result_second_hand_goods_new_and_old_degree.map(o => o.title = o['new_and_old_degree'])
	  			this.result_second_hand_goods_new_and_old_degree = result_second_hand_goods_new_and_old_degree
		 	}
		});
	},
	/**
	 * 获取buy_goods
	 */
	get_purchase_center_buy_goods(){
		this.$get("~/api/purchase_center/get_list?like=0", { page: 1, size: 10, "buy_goods": this.query.word }, (json) => {
		  if (json.result) {
			var result_purchase_center_buy_goods = json.result.list;
			result_purchase_center_buy_goods.map(o => o.title = o['buy_goods'])
	  			this.result_purchase_center_buy_goods = result_purchase_center_buy_goods
		 	}
		});
	},
	/**
	 * 获取item_type
	 */
	get_purchase_center_item_type(){
		this.$get("~/api/purchase_center/get_list?like=0", { page: 1, size: 10, "item_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_purchase_center_item_type = json.result.list;
			result_purchase_center_item_type.map(o => o.title = o['item_type'])
	  			this.result_purchase_center_item_type = result_purchase_center_item_type
		 	}
		});
	},
	/**
	 * 获取buy_goods
	 */
	get_message_notification_buy_goods(){
		this.$get("~/api/message_notification/get_list?like=0", { page: 1, size: 10, "buy_goods": this.query.word }, (json) => {
		  if (json.result) {
			var result_message_notification_buy_goods = json.result.list;
			result_message_notification_buy_goods.map(o => o.title = o['buy_goods'])
	  			this.result_message_notification_buy_goods = result_message_notification_buy_goods
		 	}
		});
	},
	/**
	 * 获取item_type
	 */
	get_message_notification_item_type(){
		this.$get("~/api/message_notification/get_list?like=0", { page: 1, size: 10, "item_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_message_notification_item_type = json.result.list;
			result_message_notification_item_type.map(o => o.title = o['item_type'])
	  			this.result_message_notification_item_type = result_message_notification_item_type
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_goods();
	this.get_article();
		this.get_buyers_buyer_account();
		this.get_second_hand_goods_new_and_old_degree();
		this.get_purchase_center_buy_goods();
		this.get_purchase_center_item_type();
		this.get_message_notification_buy_goods();
		this.get_message_notification_item_type();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_goods();
	  this.get_article();
	  this.get_buyers_buyer_account();
	  this.get_second_hand_goods_new_and_old_degree();
	  this.get_purchase_center_buy_goods();
	  this.get_purchase_center_item_type();
	  this.get_message_notification_buy_goods();
	  this.get_message_notification_item_type();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
