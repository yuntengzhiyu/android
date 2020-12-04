<template>
	<view class="zai-box">
		<image src="../../static/zaizai-login/login.png" mode='aspectFit' class="zai-logo"></image>
		<view class="zai-title">LOGO区域</view>
		<view class="zai-form">
			<input class="zai-input" v-model="username"  placeholder="请输入用户名" />
			<input class="zai-input" v-model="password"  placeholder="请输入密码"/>
			<!-- <view class="zai-label">忘记密码？</view> -->
			<button class="zai-btn" @click="login">立即登录</button>
			<navigator url="../register/register" hover-class="none" class="zai-label">还没有账号？点此注册.</navigator>
			
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				username: '',
				password: '',
				isRegister: 0,
				id: 0
			}
		},
		onLoad(options){
			this.username = '',
			this.password = ''
			if(options.isRegister != null)
				this.isRegister = options.isRegister 
			
		},
		methods:{
			login(){
				
				uni.request({
					url: this.baseURL + 'login',
					data:{
						username: this.username,
						password: this.password
					},
					success:res=>{
						
						
						if(res.data.statu === 200){
							this.id = res.data.data.id
							uni.showToast({
								title: '登录成功'
							})
							uni.setStorage({
								key: 'id',
								data: this.id,			
								success:res=>{
									
								},
			
							})
							setTimeout(()=>{
								if(this.isRegister == 1)
								{
									uni.navigateBack({
										delta: 3
									})
								}
								else{
									uni.navigateBack({
										delta:1
									})
								}
								
	
							},1000)
						}
						else{
							uni.showToast({
								title: '登录失败',
							
							})
							this.username = '',
							this.password = ''
						}
					},
					fail:res=>{

					}
				})
			}
		}
	}
</script>

<style>
	.zai-box{
		padding: 0 100upx;
		position: relative;
	}
	.zai-logo{
		width: 100%;
		width: 100%;
		height: 310upx;
	}
	.zai-title{
		position: absolute;
		top: 0;
		line-height: 360upx;
		font-size: 68upx;
		color: #fff;
		text-align: center;
		width: 100%;
		margin-left: -100upx;
	}
	.zai-form{
		margin-top: 300upx;
	}
	.zai-input{
		background: #e2f5fc;
		margin-top: 30upx;
		border-radius: 100upx;
		padding: 20upx 40upx;
		font-size: 36upx;
	}
	.input-placeholder, .zai-input{
		color: #94afce;
	}
	.zai-label{
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}
	.zai-btn{
		margin-top: 20rpx;
		background: #ff65a3;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
	}
	.zai-btn:after{
		border: 0;
	}
	/*按钮点击效果*/
	.zai-btn.button-hover{
		transform: translate(1upx, 1upx);
	}
</style>
