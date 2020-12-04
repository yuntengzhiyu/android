<template>
	<view class="center">

		<view class="center_box_bg">
			<view class="profily">
				<view class="base">
					<view class="profily_header">
							
					</view>
					<div class="nickname-left" v-if="isLogin">
						欢迎您， 
						<span>{{username}}</span>
						<button @click="logout">退出</button>
					</div>
					<div v-else class="nickname-right">
						您还没有登录
						<button type="primary" size="mini" @click="login">请点击登录</button>
						
					</div>
					<!-- <image src="../../static/fumou-center-template/setting.png" mode=""></image> -->
				</view>
			</view>
			<view class="baiban">

			</view>
			<view class="center_menu">
				<view class="menu_item" v-for="item in menus" :key="item.key" @click="toclick(item.key)">
					<image :src="item.icon" mode="aspectFill"></image>
					<text>{{item.name}}</text>
				</view>
			</view>
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				isLogin: false,
				username: '',
				menus: [{
						name: '景点收藏',
						icon: '../../static/fumou-center-template/5.png',
						key: 1,
					},
					{
						name: '美食收藏',
						icon: '../../static/fumou-center-template/5.png',
						key: 2,
					},
					// {
					// 	name: '修改个人资料',
					// 	icon: '../../static/fumou-center-template/7.png',
					// 	key: 3,
					// },
					// {
					// 	name: '帮助中心',
					// 	icon: '../../static/fumou-center-template/8.png',
					// 	key: 4,
					// },
					// {
					// 	name: '意见反馈',
					// 	icon: '../../static/fumou-center-template/9.png',
					// 	key: 5,
					// },
					// {
					// 	name: '关于我们',
					// 	icon: '../../static/fumou-center-template/10.png',
					// 	key: 6,
					// }

				],
				id: 0
			};
		},
		onShow(){
			
				
				uni.getStorage({
					key: 'id',
					success:res=>{
						
						this.id = res.data
						
						this.isLogin = true
						uni.request({
							url : this.baseURL + 'findUsername',
							data:{
								userId: this.id
							},
							success: res=>{
								this.username = res.data.data
								
							}
						})
					},
						
				})
			
		},
		methods: {
			login(){
				uni.navigateTo({
					url: '../login/login'
				})
				
			},
			logout(){
				uni.removeStorage({
					key: 'id',
					success:res=>{
						
						this.isLogin = false
					}
				})
			},
			toclick(key){
				if(key == 1){
					uni.navigateTo({
						url: '../mycollectsite/mycollectsite?id=' + this.id 
					})
				}
				else if(key == 2){
					
					uni.navigateTo({
						url: '../mycollectfood/mycollectfood?id=' + this.id
					})
				}
				else return 
			}
		},
		computed: {
			
		}
	}
</script>

<style lang="scss">
	
	page {
		height: 100%;
	}

	.profily,
	.profily_header {
		border-radius: 8px;
	}

	.center {
		margin-top: 50px;
		height: 100%;

		&_top {
			height: 18%;
			background: url('../../static/fumou-center-template/header.jpg') no-repeat 0% 50%;
			background-size: cover;

			// background: #E6E6E6;
			.mask {
				background: rgba(0, 0, 0, .4);
				height: 100%;
			}
		}

		&_box_bg {
			background: #F9F9F9;
			position: relative;

			.profily {
				position: absolute;
				width: 90%;
				// border:1px solid #F7F7F7;
				margin: 0 auto;
				top: -100upx;
				left: 5%;
				background: #FEFEFE;
				padding: 35upx;
				box-sizing: border-box;
				box-shadow: 0px 2px 5px #EDEDED;
			}
		}
	}

	.base {
		display: flex;
		align-items: center;
		// border-bottom: 2px solid #F6F6F6;
		padding-bottom: 35upx;
		position: relative;
		.profily_header {
			height: 120upx;
			width: 120upx;
			background-image: url('../../static/fumou-center-template/header.jpg');
			background-size: 100%;
		}

		text {
			margin-left: 20px;
			font-size: 30upx;
		}
		
		image{
			position: absolute;
			height: 40upx;
			width: 40upx;
			right: 0px;
			top:0px;
		}
	}

	.order_status {
		display: flex;
		justify-content: space-between;
		margin-top: 35upx;

		.status {
			width: 140upx;
			font-size: 24upx;
			text-align: center;
			letter-spacing: .5px;
			display: flex;
			flex-direction: column;
			.icon {
				width: 50upx;
				height: 50upx;
				margin: 0 auto;
				margin-bottom: 5px;
				
			}
		}
	}

	.baiban {
		// background: #FEFEFE;
		height: 100upx;
		border-top: 2px solid #eeeeee;
	}

	.center_menu {
		width: 100%;
		display: inline-block;

		.menu_item {
			font-size: 28upx;
			letter-spacing: 1px;
			padding: 14px 5%;
			background: #FEFEFE;
			overflow: hidden;
			box-sizing: border-box;
			display: flex;
			align-items: center;
			position: relative;
			border-top: 1px solid #eeeeee;
			border-bottom: 1px solid #EFEFEF;

			&:hover {
				background: #F6F6F6 !important;
			}

			&::after {
				content: '';
				width: 30upx;
				height: 30upx;
				position: absolute;
				right: 5%;
				background: url('../../static/fumou-center-template/right.png') no-repeat;
				background-size: 100%;
			}

			text:nth-of-type(1) {
				margin-left: 10px;
			}

			image {
				width: 40upx;
				height: 40upx;
			}

			&:nth-of-type(4) {
				margin-top: 10px;
			}
		}
	}

	.nickname-left{
		position: relative;
		width: 500rpx;
		height: 100rpx;
		top: 20rpx;
		padding: 10rpx;
		font-size:30rpx;
		// background-color: blue
		span{
			color: orange;
			font-size: 35rpx;
		}
		button{
			position: absolute;
			right: 0rpx;
			bottom: 50rpx;
			font-size: 30rpx;
		}
	}
	.nickname-right{
		font-size: 35rpx;
		position: relative;
		width: 500rpx;
		height: 100rpx;
		margin-top: 30rpx;
		line-height: 50rpx;
		margin-left: 10rpx;
		// background-color: black;
		button{
			position: absolute;
			right: 0;
			
		}
	}
</style>