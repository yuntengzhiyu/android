<template>
	<view class="content">
		
		<uni-search-bar @confirm="search"  @cancel="cancel"></uni-search-bar>
		<view v-if="ISsearch">
		<swiper class="swiper" indicator-dots circular autoplay :duration="duration" :interval="interval">
			<swiper-item v-for="item in swipers" :key="item.id">
				<img class="swiper-img" :src="item.imgPath" alt="">
			</swiper-item>
		</swiper>
		<ul class="display-ul">
			<li v-for="item in display_img" class="display-li" >
				
				<img :src="item.imgPath" alt="" @click="display(item.id)">
				<text>{{item.title}}</text>
				
			</li>
		</ul>
		
		<div class="discuss">
			<ul class="discuss-ul">
				<li class="discuss-li" v-for="item in discuss" >
					<div class="left-right" @click="discussclick(item.id)">
						<img :src="item.imgPath" style="width: 260rpx; height: 260rpx" alt="">
						<div class="top-bottom">
							<h4>{{item.title}}</h4>
							
							
							<text style="font-size: 23rpx;">{{item.info}}</text>
							
							<!-- 添加小图标 -->
							<!-- <span class="download">{{item.data1}}人观看 &nbsp;</span>
							<span class="download">{{item.data2}}</span>
							<span class="download">{{item.data3}}</span> -->
							
						</div>
					</div>
				</li>
			</ul>
		</div>
		</view>
		<view v-else>
			<button @click="dataimg">数据分析</button>
			<ul class="search-ul">
				<li v-for="item in searchImg" @click="toSearch(item.id)">
					<img :src="item.sitePic" alt="" >
					<div class="search-div">
						<h4>{{item.siteName}}</h4>
						<text>{{item.commentsNum}}</text>
						<text>{{item.commentsPoint}}</text>
					</div>
					<!-- <button @click.stop="add(item.id)" id="button" >添加</button> -->
					<!-- <button @click.stop="sub(item.id)" id="button" v-else>取消</button> -->
					<button class="btn1" @click.stop="add(item.id)" v-if="mycollect.findIndex(x => x == item.id) == -1 " >添加</button>
					<button class="btn2" @click.stop="sub(item.id)" v-else>取消</button>
				</li>
			</ul>
			
		</view>
	</view>
</template>

<script>
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	
	export default {
		
		data() {
			return {
				// swipers: ['https://dimg04.c-ctrip.com/images/0zg011200084963208EE1.jpg','https://dimg04.c-ctrip.com/images/0zg0s1200085h7axmB702.jpg',
				// 'https://dimg04.c-ctrip.com/images/0zg011200084963208EE1.jpg','https://dimg04.c-ctrip.com/images/0zg621200086thk9614D0.jpg'],
				swipers: [],
				duration: 500,
				interval: 1000,
				display_img:[],
				discuss:[],
				ISsearch : true,
				searchImg: [],
				userId: 0,
				mycollect: [],
				cityId: 0,
				inputValue: ''
				
			}
		},
		onShow() {
			uni.showLoading({
				title: '正在加载中'
			})
			uni.getStorage({
				key: 'id',
				success:res=>{
					this.userId = res.data
				},
				fail:res=>{
					this.mycollect = [],
					this.userId = ''
				}
			})
			
			
			
			
			if(this.inputValue.length != 0){
				
			uni.request({
				url: this.baseURL + 'getSearchFoodImg?cityId=' + this.$city1.findIndex(item => item == this.inputValue) ,
				success:res=>{
					
					this.search_img = res.data.data
					
				},
				fail:res=>{
					
				},
				complete:res=>{
					
				}
				
			})
			}
			if(this.userId.length != 0){
			uni.request({
				url: this.baseURL + 'getUserSite?userId=' + this.userId + '&cityId=' + this.cityId,
				success:res=>{
					this.mycollect = res.data.data
				},
				fail:res=>{
					
				},
				complete:res=>{
					
				}
			})
			}
			
			
			
			
			this.getSwipers(),
			this.getDisplayImg()
			this.getDiscussImg()
			uni.hideLoading()
		},
		
		computed:{
			
		},
		methods: {
			dataimg(){
				uni.navigateTo({
					url: '../dataimg/dataimg?cityId=' + this.cityId
				})
			},
			getDiscussImg(){
				uni.request({
					url : this.baseURL + 'getDiscussImg',
					success:res=>{
						this.discuss = res.data.data
						
					},
					fail:res=>{
						
					}
				})
			},
			getDisplayImg(){
				uni.request({
					url: this.baseURL + 'getDisplayImg',
						
					success:res=>{
				
						this.display_img = res.data.data
				
					},
					fail:res=>{
						
					}
				})
			},
		    getSwipers(){
				uni.request({
					url: this.baseURL + 'getSwiper',
					success:res=>{
						this.swipers = res.data.data
					},
					fail:res=>{
						
					}
				})
			},
			search(e){
				this.inputValue = e.value
				
				this.cityId = this.$city.findIndex(item => item == e.value)
			
				uni.request({
					
					url: this.baseURL + 'getSearchImg/' + this.$city.findIndex(item => item === e.value),
					success:res=>{
						
						this.searchImg = res.data.data
						this.ISsearch = false
					},
					fail:res=>{
						
					}
				})
				
				if(this.userId.length != 0){
				uni.request({
					url: this.baseURL + 'getUserSite?userId=' + this.userId + '&cityId=' + this.cityId,
					success:res=>{
						this.mycollect = res.data.data
						
						
					},
					fail:res=>{
						
					}
				})
				}
			},
			display(id){
				uni.navigateTo({
					url: '/pages/info/info?id=' + id
				})
			},
			discussclick(id){
			
				uni.navigateTo({
					url: '/pages/discussImgInfo/discussImgInfo?id=' + id
				})
			},
			cancel(){
				this.ISsearch = true
			},
			toSearch(id){
				uni.navigateTo({
					
					url: '../SearchImgInfo/SearchImgInfo?id=' + id,

				})
			},
			add(id){
				
				uni.getStorage({
					key: 'id',
					success:res=>{     //代表已经登录了
					 
					    this.userId =res.data
						uni.request({
							url : this.baseURL + 'addsite',
							data:{
								userId: this.userId,
								siteId: id
							},
							success: res=>{
							
												
								if(res.data.statu == 200){
									uni.showToast({
										title: '成功添加数据'
									})
									this.mycollect.push(id)
								}
								else{
									uni.showToast({
										title: '添加数据失败'
									})
								}
							}
						})
	
						
					},
					fail:res=>{
						uni.navigateTo({
							url: '../login/login'
						})
					}
				})
			},
			sub(id){
				uni.getStorage({
					key: 'id',
					success:res=>{
						
							uni.request({
								url : this.baseURL + 'deletesite',
								data:{
									userId: this.userId,
									siteId: id
								},
								success:res=>{
									if(res.data.statu == 200){
										uni.showToast({
											title: '成功删除数据'
										})
										this.mycollect.splice(this.mycollect.findIndex(item => item == id) , 1)
									}
									else{
										uni.showToast({
											title: '删除数据失败'
										})
										
									}
								}
							})
						
					},
					fail:res=>{
						uni.navigateTo({
							url: '../login/login'
						})
					}
				})
			}
		
		},
		components: {
			 uniSearchBar
		}
	}
</script>

<style lang="scss" scoped>
	 
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
		list-style: none;
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

		margin-top: 20rpx;
		.display-li{
			display: flex;
			flex-direction: column;
			font-size: 12px;
			img{
				width: 160rpx;
				height: 160rpx;
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
	.discuss{
		border-top: 3px solid #eeeeee;
		margin-top: 10rpx;
			.discuss-ul{
				display: flex;
				flex-direction: column;
				list-style: none;
				.discuss-li{
					
					
				/* 	display: flex;
					flex-direction: column;
					flex-wrap: wrap; */
					font-size: 13px;
					.left-right{
						display: flex;
						flex-direction: row;
						padding: 20rpx;
						border-bottom: 2px solid #eeeeee;
						
						img{
							min-width: 260rpx;
							max-width: 260rpx;
							width: 260rpx;
							min-height: 200rpx;
							max-height: 200rpx;
							height:200rpx;
							margin-right: 5px;
						}
						.top-bottom{
							display: flex;
							flex-direction: column;
							justify-content: space-between;
							text:nth-of-type(1){
								font-size: 12px;
								
							}
							
						}
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
			margin: 10rpx;
			border-bottom: 2px solid #eeeeee;
			img{
				width: 300rpx;
				height: 200rpx;
				margin-right: 30rpx;
			}
			.search-div{
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				text:nth-of-type(2){
					color: orange;
				}
			}
			.btn1{
				background-color: yellow;
			}
			.btn2{
				background-color: #eeeeee;
			}
			button{
				position: absolute;
				width: 80rpx;
				height: 80rpx;
				right: 20rpx;
				bottom: 20rpx;
				line-height: 80rpx;
				
			}
		}
	}
</style>
