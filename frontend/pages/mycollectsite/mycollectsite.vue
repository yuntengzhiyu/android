<template>
	<view>
		<view v-if="show" class="word">
			什么都没有哦!!
		</view>
		<view v-else>
		<ul class="search-ul">
			<li v-for="item in list" @click="toSearch(item.id)">
				<img :src="item.sitePic" alt="" >
				<div class="search-div">
					<h4>{{item.siteName}}</h4>
					<text>{{item.commentsNum}}</text>
					<text>{{item.commentsPoint}}</text>
				</div>
				<button @click.stop="sub(item.id)">取消</button>
			</li>
		</ul>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: 0,
				list: [],
				show: false
			}
		},
		onLoad(options){
			uni.getStorage({
				key: 'id',
				success:res=>{
					this.id = options.id
					
					uni.request({
						url : this.baseURL + 'getSiteList',
						data:{
							userId: this.id
						},
						success:res=>{
							//代表什么都没有
							
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
		methods: {
			toSearch(id){
				uni.navigateTo({
					
					url: '../SearchImgInfo/SearchImgInfo?id=' + id,
			
				})
			},
			sub(id){
				uni.request({
					url : this.baseURL + 'deletesite',
					data:{
						userId: this.id,
						siteId: id
					},
					success:res=>{
						if(res.data.statu == 200){
							uni.showToast({
								title: '成功删除数据'
							})
							uni.request({
								url : this.baseURL + 'getSiteList',
								data:{
									userId: this.id
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
		}
	}
</script>

<style lang="scss" scoped>
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
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
			button{
				position: absolute;
				width: 80rpx;
				height: 80rpx;
				right: 20rpx;
				bottom: 20rpx;
				line-height: 80rpx;
				background-color: #eeeeee;
			}
		}
	}
</style>
