<template>
	<view>
		<view class="qiun-bg-white qiun-title-bar qiun-common-mt" >
			<view class="qiun-title-dot-light">评分</view>
		</view>
		<canvas canvas-id="canvasColumn" id="canvasColumn" class="charts" @touchstart="touchColumn" >
		</canvas>
		
		
		<!-- <canvas canvas-id="canvasLineA" id="canvasLineA" class="charts" @touchmove="moveLineA" @touchend="touchEndLineA" @touchstart="touchLineA" ></canvas> -->
			
		<view class="qiun-bg-white qiun-title-bar qiun-common-mt" >
			<view class="qiun-title-dot-light">游客数目占比</view>
		</view>
		<canvas canvas-id="canvasPie" id="canvasPie" class="charts" @touchstart="touchPie"></canvas>
	</view>
</template>

<script>
	import uCharts from '../../js_sdk/u-charts/u-charts/u-charts.js';
	var _self;
	var canvaPie = null;
	var canvaColumn=null;
		var canvaLineA=null;
		var lastMoveTime = null;
		export default {
			
			data() {
				return {
					cWidth:'',
					cHeight:'',
					pixelRatio:1,
					serverData:'',
					siteName: [],
					points: [],
					datas: [],
					cityId: 0
				}
			},
			onLoad(options) {
				this.cityId = options.cityId
				uni.getSystemInfo({
					success: function (res) {
						if(res.pixelRatio>1){
							//正常这里给2就行，如果pixelRatio=3性能会降低一点
							//_self.pixelRatio =res.pixelRatio;
							this.pixelRatio =2;
						}
					}
				});
				_self = this
				// this.getdataimg(options.cityId)
				_self = this;
				//#ifdef MP-ALIPAY
				
				//#endif
				this.cWidth=uni.upx2px(750);
				this.cHeight=uni.upx2px(500);
			     this.getServerData();
			},
			methods: {
					getdataimg(cityId){
						uni.request({
							url: this.baseURL + 'getdataimg?cityId=' + cityId,
							success:res=>{
							
								this.points = res.data.data.points
							    this.siteName  =res.data.data.siteName
							
								this.datas = res.data.data.data
								
							}
						})
						
						this.getServerData()
					},
					getServerData(){
							uni.request({
								url: this.baseURL + 'getdataimg?cityId=' + this.cityId,
								success:res=>{
								
									this.points = res.data.data.points
								    this.siteName  =res.data.data.siteName
								
									this.datas = res.data.data.data
									let Column={categories: [],series:[{"name":"评分","data":[]}]}
									Column.categories = this.siteName
								
									Column.series[0].name = "评分"
									Column.series[0].data = this.points
								
									//这里我后台返回的是数组，所以用等于，如果您后台返回的是单条数据，需要push进去
									// Column.categories=res.data.data.Column.categories;
									// //这里的series数据是后台做好的，如果您的数据没有和前面我注释掉的格式一样，请自行拼接数据
									// Column.series=res.data.data.Column.series;
									// let Column2={categories:[1,2,3,4,55],series:[{"name":"评分","data":[4.7,4.8,4.9,5.0,4.6]}]}
									// let Pie = {series:[{"name":"故宫","data":128559},{"name":"颐和园","data":33739},{"name":"八达岭","data":27739},{"name":"天坛","data":8696},{"name":"圆明园","data":18739}]}
									let Pie = {series:[]}
									Pie.series = this.datas
									// let LineA={categories:["1","2","3","4","5"],series:[{"name":"评分","data":[4.7,4.8,4.9,5.0,4.6]}]};
									
									this.showColumn("canvasColumn",Column);
									// _self.showColumn("canvasColumn2",Column2);
									this.showPie("canvasPie",Pie);
								}
							})
							//下面这个根据需要保存后台数据，我是为了模拟更新柱状图，所以存下来了
							// _self.serverData=res.data.data;
							// let Column={categories:["故宫","颐和园","八达岭长城","天坛","圆明园"],series:[{"name":"评分","data":[4.7,4.8,4.9,4.7,4.6]}]};
							
							// _self.showLineA("canvasLineA",LineA);
						
					},
				showColumn(canvasId,chartData){
					canvaColumn=new uCharts({
						$this:_self,
						canvasId: canvasId,
						type: 'column',
						legend:true,
						fontSize:11,
						background:'#FFFFFF',
						pixelRatio:_self.pixelRatio,
						animation: true,
						categories: chartData.categories,
						series: chartData.series,
						xAxis: {
							disableGrid:true,
						},
						yAxis: {
							min: 3.7,
							max: 5.0
						},
						dataLabel: true,
						width: _self.cWidth*_self.pixelRatio,
						height: _self.cHeight*_self.pixelRatio,
						extra: {
							column: {
							  width: _self.cWidth*_self.pixelRatio*0.45/chartData.categories.length,
							  
							}
						  }
					});
				},
				showPie(canvasId,chartData){
					canvaPie=new uCharts({
						$this:_self,
						canvasId: canvasId,
						type: 'pie',
						fontSize:11,
						padding:[15,15,0,15],
						legend:{
							show:true,
							padding:5,
							lineHeight:11,
							margin:0,
						},
						background:'#FFFFFF',
						pixelRatio:_self.pixelRatio,
						series: chartData.series,
						animation: true,
						width: _self.cWidth*_self.pixelRatio,
						height: _self.cHeight*_self.pixelRatio,
						dataLabel: true,
						extra: {
							pie: {
				  border:true,
				  borderColor:'#FFFFFF',
				  borderWidth:3
							}
						},
					});
				},
				touchPie(e){
					canvaPie.showToolTip(e, {
						format: function (item) {
							return item.name + ':' + item.data 
						}
					});
					canvaPie.touchLegend(e,{animation:true});
				},
				touchColumn(e){
						canvaColumn.showToolTip(e, {
							format: function (item, category) {
								if(typeof item.data === 'object'){
									return category + ' ' + item.name + ':' + item.data.value 
								}else{
									return category + ' ' + item.name + ':' + item.data 
								}
							}
						});
				canvaColumn.touchLegend(e,{animation:true});
					},
					showLineA(canvasId,chartData){
									canvaLineA=new uCharts({
										$this:_self,
										canvasId: canvasId,
										type: 'line',
					colors:['#facc14', '#f04864', '#8543e0', '#90ed7d'],
										fontSize:11,
										padding:[15,15,0,15],
										legend:{
											show:true,
											padding:5,
											lineHeight:11,
											margin:0,
										},
										dataLabel:false,
										dataPointShape:true,
										background:'#FFFFFF',
										pixelRatio:_self.pixelRatio,
										categories: chartData.categories,
										series: chartData.series,
										animation: true,
										xAxis: {
											type:'grid',
											gridColor:'#CCCCCC',
											gridType:'dash',
											dashLength:8,
											//disableGrid:true
										},
										yAxis: {
											gridType:'dash',
											gridColor:'#CCCCCC',
											dashLength:8,
										},
										width: _self.cWidth*_self.pixelRatio,
										height: _self.cHeight*_self.pixelRatio,
										extra: {
											line:{
												type: 'straight'
											}
										}
									});
									
								},
								touchLineA(e) {
									lastMoveTime=Date.now();
								},
								moveLineA(e){
									let currMoveTime = Date.now();
									let duration = currMoveTime - lastMoveTime;
									if (duration < Math.floor(1000 / 60)) return;//每秒60帧
									lastMoveTime = currMoveTime;
									
									let currIndex=canvaLineA.getCurrentDataIndex(e);
									if(currIndex>-1&&currIndex<canvaLineA.opts.categories.length){
										let riqi=canvaLineA.opts.categories[currIndex];
										let leibie=canvaLineA.opts.series[0].name;
										let shuju=canvaLineA.opts.series[0].data[currIndex];
										this.Interactive=leibie+':'+riqi+'-'+shuju+'元';
									}
									canvaLineA.showToolTip(e, {
										format: function (item, category) {
											return category + ' ' + item.name + ':' + item.data 
										}
									});
								},
								touchEndLineA(e){
									let currIndex=canvaLineA.getCurrentDataIndex(e);
									if(currIndex>-1&&currIndex<canvaLineA.opts.categories.length){
										let riqi=canvaLineA.opts.categories[currIndex];
										let leibie=canvaLineA.opts.series[0].name;
										let shuju=canvaLineA.opts.series[0].data[currIndex];
										this.Interactive=leibie+':'+riqi+'-'+shuju+'元';
									}
									canvaLineA.touchLegend(e);
									canvaLineA.showToolTip(e, {
										format: function (item, category) {
											return category + ' ' + item.name + ':' + item.data 
										}
									});
								},
			}
		}
</script>

<style lang="scss" scoped>
@import '../common/index.css';
#canvasColumn{
	// margin-top: 30rpx;
}
</style>
