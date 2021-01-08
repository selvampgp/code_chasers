<template>
  <div class="layout-picture" id="app">
    <layout msg="Recruitment  portal"/>
    <chat-bot-card />
  </div>
</template>

<script>
import Layout from './components/Layout.vue'
import ChatBotCard from './components/ChatBotCard.vue'

export default {
  name: 'App',
  components: {
    Layout,
    ChatBotCard
  }
}
</script>

<style scoped lang="stylus">
  .layout-picture
    height 700px
    background-image url('./assets/layout.png')
    background-repeat no-repeat
    background-size cover
    background-position-y bottom

</style>
