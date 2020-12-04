<template>
	<view>
		
		<swiper class="swiper" indicator-dots circular autoplay :duration="duration" :interval="interval">
			<swiper-item v-for="(item,index) in sitePic" :key="item.id">
				<img class="swiper-img" :src="item" alt="">
			</swiper-item>
		</swiper>
		<h2>{{siteName}}</h2>
		<div class="intro">
			<span>地址为:  </span>
			<span>{{siteAddress}}</span>
			<div>
			<span>电话为:</span>
			<span>{{sitePhone}}</span>
			</div>
		</div>
		<div class="intro_word">
			<p>景点介绍:</p>
			<p>{{siteIntro}}</p>
		</div>
		<view v-if="this.sitePolicy == null? false: true" class="policy">
			<p>景点政策</p>
			<p>{{sitePolicy}}</p>
		</view>
		<div class="othersite"> 
			<ul>
				<li v-for="item in othersite">
					<img :src="item.othersitePic" alt="">
					<p>{{item.othersiteName}}</p>
					<p>{{item.othersiteDis}}</p>
				</li>
			</ul>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				siteName: '',
				siteAddress: '',
				sitePic: [],
				othersite: [],
				sitePhone: '',
				siteIntro: '',
				sitePolicy: '',
				duration: 1000,
				interval: 2000
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
					url: this.baseURL + 'getSearchImgInfo/' + id,
						
					success:res=>{
						const datas = res.data.data
						this.siteName = datas.siteName
						this.siteAddress = datas.siteAddress
						this.sitePic = datas.sitePic
						this.othersite = datas.othersite
						this.sitePhone = datas.sitePhone
						this.Intro = datas.Intro
						this.sitePolicy = datas.sitePolicy
						this.siteIntro = datas.siteIntro
						uni.hideLoading()
					}
				})
			}
		},
		compoted:{
			
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
	h2{
		margin-top: 10rpx;
		text-align: center;
	}
	.intro{
		width: 750rpx;
		border: 2px solid #708090;
		// border-top: 2px solid #eeeeee;
		// border-bottom: 2px solid #eeeeee;
		span:nth-child(1){
			font-size: 17px;
		}
		span:nth-child(2){
			font-size: 18px;
			font-weight: bold;
			
		}
		
	}
	.policy ,.intro_word{
		p:nth-child(1){
			color: red;
			font-size: 18px;
			font-weight: bold;
		}
		p:nth-child(2){
			text-indent: 20px;
			line-height: 30px;
		}
	}
	.othersite{
		border: 2px solid #eeeeee;
		ul{
			display: flex;
			flex-direction: row;
			justify-content: space-around;
			margin-top: 30rpx;
			li{
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				width: 200rpx;
				height: 300rpx;
				font-size: 26rpx;
				margin-bottom: 20rpx;
				img{
					
					width: 100%;
					height: 60%;
					box-shadow: 5px 5px 5px #eeeeee;
				}
				p{
					margin: 5rpx;
				}
				
			}
		}
	}
</style>
