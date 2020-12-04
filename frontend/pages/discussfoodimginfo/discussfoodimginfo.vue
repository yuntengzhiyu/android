<template>
	<view>
		<span style="font-size: 40rpx; color: orange;">{{title}}</span>
		<span>的做法步骤</span>
			
			<ul>
				<li v-for="item in other">
					<img :src="item.img" alt="">
					<text>{{item.word}}</text>
				</li>
			</ul>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				other: [],
				title: ''
			}
		},
		methods: {
			
		},
		onLoad(options){
			uni.showLoading({
				title: '正在加载中'
			})
			uni.request({
				url: this.baseURL + 'getDiscussFoodImgInfo?id=' + options.id,
				success:res=>{
					
					this.other = res.data.data.other
					this.title = res.data.data.title
				}
			})
			
		},
		onReady(){
			uni.hideLoading()
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
ul{
	display: flex;
	flex-direction: column;
	
	li{
		display: flex;
		position: relative;
		flex-direction: row;
		margin-top: 10rpx;
		border: 2px solid #eeeeee;
		
		img{
			width: 350rpx;
			height: 350rpx;
			padding: 20rpx;
		}
		text{
			display: block;
			width: 350rpx;
			font-size: 35rpx;
			
			
		}
	}
}
</style>
