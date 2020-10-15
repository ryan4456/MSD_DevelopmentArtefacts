<template>
	<view>pop up price</view>
</template>

<script>
	export default {
	    name: 'UniPopupPrice',
	    props: {
	        title: {
	            type: String,
	            default: '选择认筹数量'
	        }
	    },
	    // 直接把下面这一行代码，放到自己的组件内
	    inject: ['popup'],
	    // ...
	    methons:{
	        /**
	         * 定义的选择事件，选择内容后触发
	         */
	        select(item, index) {
	            // 将事件发送到页面，在页面进行监听
	            this.$emit('select', {
	                item,
	                index
	            }, () => {
	                // 延迟操作，执行父组件的close事件，关闭弹出层
	                this.popup.close()
	            })
	        },
	        /**
	         * 关闭窗口
	         */
	        close() {
	            // 执行父组件的close事件，关闭弹出层
	            this.popup.close()
	        }
	    }
	}
</script>

<style>
</style>
