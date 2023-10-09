<template>
  <div class="box">
    <div class="content">
      <div id="player"></div>
      <!-- <input type="button" value="start" @click="start()" /> -->
    </div>
  </div>
</template>
<script>


import WasmPlayer from "@easydarwin/easywasmplayer"
const video_url ="https://sf1-hscdn-tos.pstatp.com/obj/media-fe/xgplayer_doc_video/flv/xgplayer-demo-360p.flv";
export default {
  components: {

  },
  data() {
    return {};
  },
  mounted() {
    console.log("mounted");
    try {
      this.player = new WasmPlayer(null, "player", (e) => {
        console.log(e);
      });
      this.player.play(video_url, 1); // 自动播放
    } catch (ee) {
      console.log(ee);
    }
  },
  methods: {
    start() {
      console.log("here");
      // this.player.destroy();
      // this.player.load();
      this.player.play(video_url, 1);
    },
  },
};
</script>
<style scoped>
.header {
  height: 30px;
  border: 1px solid black;
}
.box {
  display: flex;
  height: 100%;
}
.box .nav {
  flex: 1;
  border: 1px solid blue;
  height: 610px;
  width: 200px;
}
.box .content {
  flex: 5;
  border: 1px solid blue;
  height: 100%;
  width: 100%;
}
</style>
