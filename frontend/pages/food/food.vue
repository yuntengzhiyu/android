<template>
	<view>
		<uni-search-bar @confirm="search"  @cancel="cancel" ></uni-search-bar>
		<view v-if="isSearch">
		<swiper class="swiper" indicator-dots circular autoplay :duration="duration" :interval="interval">
			<swiper-item v-for="item in swiper" :key="item.id">
				<img class="swiper-img" :src="item.imgPath" alt="">
			</swiper-item>
		</swiper>
		<ul class="display-ul">
			<li v-for="item in display_img" class="display-li" >
				
				<img :src="item.imgPath" alt="" @click="display(item.id)">
				<text>{{item.title}}</text>
				
			</li>
		</ul>
		<h3 style="text-align: center; margin-top: 20rpx;">原创食谱</h3>
		<ul class="discuss-ul">
			<li v-for="item in discuss_img" class="discuss-li" @click="todiscuss(item.id)">
				<img :src="item.imgPath"  alt=""  style="width: 300rpx; height: auto;">
				<div class="top-bottom">
					<span>{{item.title}}</span>
					<span>{{item.material}}</span>
				</div>
			</li>
		</ul>
		</view>
		<view v-else>
			<button @click="todataimg">数据分析</button>
			<ul class="search-ul">
				<li class="search-li" v-for="item in search_img" :key="item.siteId">
					<img :src="item.imgPath" alt="">
					<div class="top-bottom">
						<h5>{{item.title}}</h5>
						<div class="left-right">
							<span>评分为:</span>
							<span>{{item.score}}</span>
							<span>{{item.commentsNum}}</span>
							<span>条评论</span>
						</div>
						<span>地址为:</span>
						<div class="address">{{item.address}}</div>
						<text>人均</text>
						<text>￥{{item.avgPrice}}</text>
					</div>
					<button class="btn1" @click.stop="add(item.siteId)" v-if="mycollect.findIndex(x => x == item.siteId) == -1 " >添加</button>
					<button class="btn2" @click.stop="sub(item.siteId)" v-else>取消</button>
					
					<!-- <button @click.stop="sub(item.siteId)" v-else>取消</button> -->
				</li>
				
			</ul>
			
			
		</view>
	</view>
</template>

<script>
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	export default {
		components:{
			 uniSearchBar
		},
		data() {
			return {
				swiper: [],
				duration: 1000,
				interval: 2000,
				display_img: [],
				discuss_img: [],
				search_img: [],
				isSearch: true,
				userID: '',
				mycollect: [],
				cityId: 0,
		
				inputValue: ''
			}
		},
		onShow(){
			uni.showLoading({
				title: '正在加载'
			})
			
			
			
			
			
			
			
			
			
			//是否登录
			uni.getStorage({
				key: 'id',
				success:res=>{
				
					this.userID = res.data
				},
				fail:res=>{
					this.userID = '',
					this.mycollect = []
				}
			})
			
			
			if(this.inputValue.length != 0){
				
			uni.request({
				url: this.baseURL + 'getSearchFoodImg?cityId=' + this.$city1.findIndex(item => item == this.inputValue) ,
				success:res=>{
					
					this.search_img = res.data.data
				
				},
				
			})
			}
			if(this.userID.length != 0){
			uni.request({
				url: this.baseURL + 'getUserFood?userId=' + this.userID + '&cityId=' + this.cityId,
				success:res=>{
					this.mycollect = res.data.data
				
					
				}
			})
			}
			
			
			
			
			
			
			uni.request({
				url: this.baseURL + 'getFoodSwiper',
				success:res=>{
					this.swiper = res.data.data
				},
	
			})
			
			uni.request({
				url : this.baseURL + 'getDisplayFoodImg',
				success: res=>{
					this.display_img = res.data.data
				}
			})
			uni.request({
				url: this.baseURL + 'getDiscussFoodImg',
				success:res=>{
					
					this.discuss_img = res.data.data
					uni.hideLoading()
				}
			})
		},
		computed:{
			
		},
		methods: {
			todataimg(){
				uni.navigateTo({
					url: '../fooddataimg/fooddataimg?cityId=' + this.cityId
				})
			},
			display(id){
				
				uni.navigateTo({
					url: '../DisplayFoodImgInfo/DisplayFoodImgInfo?id=' + id
				})
			},
			todiscuss(id){
				uni.navigateTo({
					url: '../discussfoodimginfo/discussfoodimginfo?id=' + id
				})
			},
			cancel(){
				this.isSearch = true
			},
			search(e){
				this.inputValue = e.value
				
				this.cityId = this.$city1.findIndex(item=>item == e.value)
				
				uni.request({
					url: this.baseURL + 'getSearchFoodImg?cityId=' + this.$city1.findIndex(item => item == e.value) ,
					success:res=>{
						
						this.search_img = res.data.data
						this.isSearch = false
					},
					
				})
				if(this.userID.length != 0){
				uni.request({
					url: this.baseURL + 'getUserFood?userId=' + this.userID + '&cityId=' + this.cityId,
					success:res=>{
					
						this.mycollect = res.data.data
						
					}
				})
				}
			},
			add(siteId){
				uni.getStorage({
					key: 'id',
					success:res=>{
						this.userID = res.data
						uni.request({
							url : this.baseURL + 'addfood',
							data:{
								userId: this.userID,
								siteId: siteId
							},
							success: res=>{
							
												
								if(res.data.statu == 200){
									uni.showToast({
										title: '成功收藏数据'
									})
									this.mycollect.push(siteId)
								
								}
								else{
									uni.showToast({
										title: '收藏数据失败'
									})
								}
							}
						})
						
					},
					fail(){
						uni.navigateTo({
							url: '../login/login'
						})
					}
				})
			},
			sub(siteId){
				uni.getStorage({
					key: 'id',
					success:res=>{
						uni.request({
							url: this.baseURL + 'deletefood',
							data:{
								userId: this.userID,
								siteId: siteId
							},
							success:res=>{
								if(res.data.statu == 200){
									uni.showToast({
										title: '成功取消收藏'
									})
									this.mycollect.splice(this.mycollect.findIndex(item => item == siteId), 1)
								}
								else{
									uni.showToast({
										title: '取消收藏失败'
									})
								}
							}
						})
					},
					fail(){
						uni.navigateTo({
							url: '../login/login'
						})
					}
				})
			}
		
		}
	}
</script>

<style lang="scss" scoped>
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
	.swiper{
		width: 100%;
		height: 400rpx;
		img{
			width: 100%;
			height: 100%;
		}
	}
.display-ul{
		list-style: none;
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		// background-color: blue;
		// border: 2px solid #eeeeee;
		margin-top: 20rpx;
		.display-li{
			display: flex;
			flex-direction: column;
			margin-left: 30rpx;
			font-size: 15px;
			border: 2px solid #eeeeee;
			img{
				text-align: center;
				width: 230rpx;
				height: 230rpx;
				margin-left: 45rpx;
				margin-right: 40rpx;
				border-radius: 50%;
			
			}

			text{
				text-align: center;
				margin-top: 10rpx;
				margin-bottom: 10rpx;
			}
		
		}
	}
	.discuss-ul{
		display: flex;
		flex-direction: column;

		
		li{
			display: flex;
			flex-direction: row;

			height: 300rpx;
			// background-color: black;
			padding: 0rpx;
			margin: 5rpx;
			border: 2px solid #eeeeee;
			font-size: 34rpx;
			img{
				border-radius: 50%;
				width: 300rpx;
				height: 300rpx;
				margin-right: 20rpx;
			}
			div{
				display: flex;
				flex-direction: column;
				span:nth-child(1){
					font-size: 20px;
					margin-top: 20rpx;
					color: #007AFF;
				}
				span:nth-child(2){
					font-size: 18px;
					margin-top: 10rpx;
				}
			}
		}
	}
	.search-ul{
		display: flex;
		flex-direction: column;
		
		
		li{
			position: relative;
			display: flex;
			flex-direction: row;
			width: 750rpx;
			height: 220rpx;
			border: 2px solid #eeeeee;
			padding: 5rpx;
			img{
				width: 260rpx;
				height: 200rpx;
				margin-right: 15rpx;
			}
			.top-bottom{
				
				width: 350rpx;
				height: 300rpx;
				h5{
					text-overflow: ellipsis;
					overflow: hidden;
					white-space: nowrap;
				}
				.left-right{
					span:nth-child(1){
						font-size: 25rpx;
						margin-right: 10rpx;
						font-weight: bold;
					}
					span:nth-child(2){
						font-size: 30rpx;
						color: orange;
						margin-right: 25rpx;
					}
					span:nth-child(3){
						font-size: 30rpx;
						color: orange;
						
					}
					span:nth-child(4){
						font-size: 25rpx;
					
					}
				}
				span{
					font-size: 25rpx;
					font-weight: bold;
				}
				.address{
					margin-top: -40rpx;
					margin-left: 90rpx;
				
					font-weight: bold;
					font-size: 28rpx;
					overflow: hidden;
					white-space: nowrap;
			        text-overflow:ellipsis;
				}
				text:nth-of-type(1){
					font-size: 25rpx;
					font-weight: bold;
					margin-right: 20rpx;
				}
				text:nth-of-type(2){
					font-weight: bold;
					color: orange;
				}
			}
			
			button{
				position: absolute;
				right: 20rpx;
				bottom: 20rpx;
				width: 80rpx;
			}
			.btn1{
				background-color: yellow;
			}
			.btn2{
				background-color: #eeeeee;
			}
		}
	}
</style>
