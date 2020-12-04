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
				title: '正在加载'
			})
			uni.request({
				url: this.baseURL + 'getDisplayFoodImgInfo?id=' + options.id,
				success:res=>{
				
					this.title = res.data.data.title
					this.other = res.data.data.other
				
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
			
		}
		text{
			position: absolute;
			top: 50%;
			left: 65%;
			transform: translate(-50%,-50%);
			
		}
	}
}
</style>
