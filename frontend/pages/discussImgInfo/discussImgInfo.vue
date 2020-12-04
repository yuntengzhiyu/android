<template>
	<view>
		<rich-text :nodes="content"></rich-text>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				content: ''
			}
			
		},
		onLoad(options){
			uni.showLoading({
				title: '正在加载'
			})
			this.getInfo(options.id)
			uni.hideLoading()
		},
		methods: {
			getInfo(id){
				uni.request({
					url: this.baseURL + 'getDiscussImgInfo/' + id,
					
					success:res=>{
						
						this.content = res.data.data.content

						this.content = this.content.replace(/\<img/gi, '<img style="width:100%;height:auto"')
						this.content = this.content.replace(/\<p/gi, '<p style="text-indent: 24px; line-height:30px;"')
					
					}
				})
			}
		}
	}
</script>

<style>

</style>
