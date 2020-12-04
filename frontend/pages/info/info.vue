<template>
	<view>
		<div class="content">
			<div v-for="(item, index) in info" :key="index" class="item">
				<img :src="item.otherImg" alt="">
				<h4>{{item.otherTitle}}</h4>
				<span>{{item.otherScore}}分</span>
				<button >已经卖完</button>
			</div>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info : []
			}
		},
		onLoad(options){
			uni.showLoading({
				title: '正在加载中'
			})
			this.getInfo(options.id)
		},
		methods: {
			
			getInfo(id){
				uni.request({
					url: this.baseURL + 'getDisplayImgInfo/' + id,
					success:res=>{
						
						this.info = res.data.data
						uni.hideLoading()
					}
				})
			}
		}
	}
</script>

<style lang=scss>
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
	.content{
		
		width: 750rpx;
		
		
		div{
			position: relative;
			display: flex;
			flex-direction: column;
			justify-content: space-around;
			margin-bottom: 20rpx;
			margin-left: 120rpx;
			height: 520rpx;
			/* background-color: blue; */
			box-shadow: 10px 10px 10px #eeeeee;
			border: 2px solid #eeeeee;
			width: 500rpx;
			img{
				width: 500rpx ;
				height: 350rpx;
				text-align: center;
				
			}
			span{
				font-size: 16px;
				font-weight: bold;
				color: orange;
			}
			button{
				position: absolute;
				right: 10rpx;
				bottom: 10rpx;
				background-color: #eeeeee;
				width: 100rpx;
				height: 30rpx;
				display: inline-block;
				line-height: 30rpx;
				font-size: 20rpx;
			}
			
			
		}
	}
</style>
