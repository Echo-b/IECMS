<template>
  <div id="imgbox">
    <img style="height: 525px; width: 525px; margin-right: 2rem;" src="http://192.168.72.62/mjpeg/1">
    <img style="height: 525px; width: 525px; margin-right: 2rem;" src="http://192.168.72.62/mjpeg/1">
  </div>
</template>

<style>
.imgbox{
  display: flex;
}
</style>
<!-- <script>
var video = document.getElementById('video')
var url = 'http://192.168.183.62/mjpeg/1'
// Fetch the video stream
fetch(url)
  .then(function(response) {
    var reader = response.body.getReader()
    // Read data from the stream
    function read() {
      reader.read().then(function(result) {
        if (!result.done) {
          // Display the video frame
          var blob = new Blob([result.value], { type: 'image/jpeg' })
          var objectUrl = URL.createObjectURL(blob)
          video.src = objectUrl
          // Continue reading data from the stream
          read()
        }
      })
    }
    // Start reading data from the stream
    read()
  })
  .catch(function(error) {
    console.error(error)
  })
</script> -->
