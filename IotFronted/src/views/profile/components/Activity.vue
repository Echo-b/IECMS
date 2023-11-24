<template>
  <div class="user-activity">
    <div v-for="(item, index) of activities" :key="index" class="post">
      <div class="user-block">
        <img class="img-circle" :src="item.avatar">
        <span class="username text-muted">{{ item.username }}</span>
        <span class="description">{{ item.command }} - {{ item.timestap }}</span>
      </div>
      <p>
        {{ item.summary }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activities: [
        {
          avatar: 'https://wpimg.wallstcn.com/9e2a5d0a-bd5b-457f-ac8e-86554616c87b.jpg',
          username: 'Spider Man',
          command: 'Shared publicly',
          timestap: '7:30 PM today',
          summary: 'Lorem ipsum represents a long-held tradition for designers'
        },
        {
          avatar: 'https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg',
          username: 'Captain American',
          command: 'Sent you a message',
          timestap: 'yesterday',
          summary: 'Lorem ipsum represents a #### long-held tradition for designers'
        },
        {
          avatar: 'https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg',
          username: 'EchoBai',
          command: 'test',
          timestap: 'now',
          summary: 'test it and watch it'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.user-activity {
  .user-block {

    .username,
    .description {
      display: block;
      margin-left: 50px;
      padding: 2px 0;
    }

    .username{
      font-size: 16px;
      color: #000;
    }

    :after {
      clear: both;
    }

    .img-circle {
      border-radius: 50%;
      width: 40px;
      height: 40px;
      float: left;
    }

    span {
      font-weight: 500;
      font-size: 12px;
    }
  }

  .post {
    font-size: 14px;
    border-bottom: 1px solid #d2d6de;
    margin-bottom: 15px;
    padding-bottom: 15px;
    color: #666;

    .image {
      width: 100%;
      height: 100%;

    }

    .user-images {
      padding-top: 20px;
    }
  }

  .list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;

    li {
      display: inline-block;
      padding-right: 5px;
      padding-left: 5px;
      font-size: 13px;
    }

    .link-black {

      &:hover,
      &:focus {
        color: #999;
      }
    }
  }

}

.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}
</style>
