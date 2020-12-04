<template>
	<view>
		<view v-if="show" class="word">
			什么都没有哦!!
		</view>
		<view v-else>
			
		<ul class="search-ul">
			<li class="search-li" v-for="item in list" :key="item.siteId">
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
				<button @click.stop="sub(item.siteId)">取消</button>
				
			</li>
			
		</ul>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [],
				userId: 0,
				show: false
			}
		},
		methods: {
			sub(id){
				uni.request({
					url : this.baseURL + 'deletefood',
					data:{
						userId: this.userId,
						siteId: id
					},
					success:res=>{
						if(res.data.statu == 200){
							uni.showToast({
								title: '成功删除数据'
							})
							uni.request({
								url : this.baseURL + 'getFoodList',
								data:{
									userId: this.userId
								},
								success:res=>{
									if(res.data.statu == 1)
										this.show = true
									this.list = res.data.data
									
								}
							})
						}
						else{
							uni.showToast({
								title: '删除数据失败'
							})
						}
					}
				})
			}
		},
		onLoad(options){
			uni.getStorage({
				key: 'id',
				success:res=>{
					this.userId = options.id
					uni.request({
						url : this.baseURL + 'getFoodList',
						data:{
							userId: options.id
						},
						success:res=>{
							if(res.data.statu == 1)
								this.show = true
							this.list = res.data.data
							
						}
					})
				},
				fail:res=>{
					uni.showToast({
						title: '请先登录!'
					})
				}
			})
			
		},
		
	}
</script>

<style lang="scss" scoped>
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
		list-style:none;
	}
	.word{
		font-size: 40rpx;
		font-weight: bold;
		text-align: center;
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
				background-color: #eeeeee;
			}
		}
	}

</style>
