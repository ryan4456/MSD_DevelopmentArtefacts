<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
		},
		onHide: function() {
			uni.closeSocket({
				reason: 'Application hide'
			})
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>
