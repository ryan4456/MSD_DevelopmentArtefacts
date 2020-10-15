<template>
	<view class="progress-box">
		<view class="progress-box-w" v-bind:style="[{background:progressColor,width:getWid}]">
			 <view class="progress-box-w-w" v-bind:style="{background:progressBckColor}" :animation="animationData" >
				 <view class="progress-box-w-n" v-bind:style="{background:progressColor, width: mobilePx+'px'}"></view>
			 </view>
		</view>
		<view v-bind:style="{height: '1px', width: '100%', display: 'block'}">
			<view class="progress-arrow" v-bind:style="{height: '1px', width: '1px', 'margin-left': tipPx+'px'}">
			</view>
		</view>
		<view class="progress-box-sgin" v-bind:style="{width:getWid+'px'}">
			<view id="tip" class="progress-box-sgin-n" v-bind:style="{background:contentBacColor, 'margin-left': tipPx + alignPx + 'px'}">
				<text v-bind:style="{color:contentColor}">{{content}}</text>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		created: function() {
			// var animation = uni.createAnimation({
			// 	duration: this.time,
			// 	timingFunction: this.type,
			// })
			// this.animation = animation
			this.cpMobile()
			// setTimeout(function() {
			// 	animation.translate(this.mobilePx).step()
			// 	this.animationData = animation.export()
			// }.bind(this), 100)
		},
		mounted() {
			const query = uni.createSelectorQuery().in(this);
			const that = this;
			query.select('#tip').boundingClientRect(data => {
			  if(parseInt(that.getWid) - data.width < that.tipPx){
				  that.alignPx = parseInt(that.getWid) - data.width - that.tipPx - 30;
			  }
			}).exec();
		},
		props:{
			nowD:{},
			sumD:{},
			content:'',
			contentColor:{
				type: String,
				default: 'rgba(3,144,252,1)'
			},
			contentBacColor:{
				type: String,
				default: '#F4F4F4'
			},
			time:{
				type: Number,
				default: 1000
			},
			progressBckColor:{
				type: String,
				default: 'rgba(184, 224, 255,1)'
			},
			progressColor:{
				type: String,
				default: '#FFFFFF'
			},
			wid:{
				type: Number,
				default: 280
			},
			type:{
				type: String,
				default: "ease"
			}
		},
		computed:{
			getWid(){
				let width = uni.upx2px(this.wid);
				return width+"px";
			},
			getSginWid(){
				return (uni.upx2px(this.wid)+40).toString()+"rpx"
			}
		},
		data() {
			return {
				animationData: {},
				mobilePx:0,
				tipPx: 0,
				alignPx: 0
			};
		},
		methods: {
			cpMobile(){
				this.mobilePx= uni.upx2px(this.nowD/this.sumD*this.wid)
				this.tipPx = this.mobilePx - uni.upx2px(16);
			}
		}
	}
</script>

<style >
	
	.progress-box {
		display: flex;
		margin: auto auto;
		flex-direction: column;
	}
	
	.progress-box-sgin{
		height: 80rpx;
		margin: auto auto;
		width: 100%;
	}
	.progress-box-sgin-n{
		display: inline-block;
		text-align: center;
		line-height: 41rpx;
		height: 41rpx;
		border-radius: 10rpx;
		padding-left: 10rpx;
		padding-right: 10rpx;
		margin-top: 15rpx;
	}
	.progress-box-sgin-n>text{
		height:41rpx;
		font-size:24rpx;
		font-family:SFProText-Semibold;
		line-height:41rpx;
		opacity:1;
	}
	.progress-arrow{
		margin-top: -30rpx;
	}
	.progress-arrow:after{
		content: '';
		width: 0;
		height: 0;
		margin: auto auto;
		border: 16rpx solid #F4F4F4;
		border-color:  transparent transparent #ECA63F transparent;
	}
	.progress-box-w {
		overflow:hidden;
		/* margin: auto auto; */
		border-radius: 5rpx;
		background-color:  #FFFFFF;
		height: 10rpx;
		margin-top: 30rpx;
	}
	.progress-box-w-w{
		border-radius: 5rpx;
		width: 100%;
		height: 10rpx;
	}
	.progress-box-w-n{
		height: 10rpx;
		background-color: #F9975F;
		width: 20rpx;
	}
</style>
