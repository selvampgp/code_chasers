<template>
    <div class="chatBotCard">
        <beautiful-chat class="popCard" :participants="participants" :title-image-url="titleImageUrl" :on-message-was-sent="onMessageWasSent" :message-list="messageList" :new-messages-count="newMessagesCount" :is-open="isChatOpen" :close="closeChat" :icons="icons"
            :open="openChat" :show-emoji="false" :show-file="false" :show-edition="false" :show-deletion="false" :show-typing-indicator="showTypingIndicator" :show-launcher="true" :show-close-button="true" :colors="colors" :always-scroll-to-bottom="alwaysScrollToBottom"
            :message-styling="messageStyling" @onType="handleOnType">
            <template v-slot:header>
            <div class="chat-bot-header">
                <br/>
              <div class="chat-bot-header__content">i-Bot</div>
            </div>
            </template>
            <template v-slot:system-message-body="scopedProps">
                <div class="system-message">
                    <slot name="system-message-body" :message="scopedProps.message">{{ todayDate }}</slot>
                </div>
            </template>
            <template v-slot:text-message-body="{ message }">
                <!-- eslint-disable-next-line vue/no-v-html -->
                <div class="msgBody" v-html="message.data.text"></div>
            </template>
    </beautiful-chat>
  </div>
</template>

<script>
import CloseIcon from 'vue-beautiful-chat/src/assets/close-icon.png';
import OpenIcon from 'vue-beautiful-chat/src/assets/logo-no-bg.svg';
import FileIcon from 'vue-beautiful-chat/src/assets/file.svg';
import CloseIconSvg from 'vue-beautiful-chat/src/assets/close.svg';
import moment from 'moment';

export default {
    name: 'ChatBotCard',
    data() {
        return {
            isChatOpen: false,
            visible: false,
            channel: '',
            todayDate: moment().format('DD-MMM-YYYY'),
            icons: {
                open: {
                    img: OpenIcon,
                    name: 'default',
                },
                close: {
                    img: CloseIcon,
                    name: 'default',
                },
                file: {
                    img: FileIcon,
                    name: 'default',
                },
                closeSvg: {
                    img: CloseIconSvg,
                    name: 'default',
                },
            },
            participants: [],
            messageList: [],
            intialMessageList: [{
                    id: 1,
                    type: 'system',
                    data: {
                        text: '',
                    },
                    stamp: '',
                },
                {
                    id: 2,
                    type: 'text',
                    author: 'system',
                    data: {
                        text: 'Thank you, for choosing i-Bot. Please choose your options',
                    },
                    suggestions: ['1. Previous interview status','2. Upcoming interview details'],
                    stamp: moment().fromNow(),
                },
            ],
            titleImageUrl: '',
            newMessagesCount: 1,
            showTypingIndicator: '',
            colors: {
                header: {
                    bg: '#012964',
                    text: '#ffffff',
                },
                launcher: {
                    bg: '#00AEEF',
                },
                messageList: {
                    bg: '#CBD1CA',
                    text: '#565867',
                },
                sentMessage: {
                    bg: '#FF540A',
                    text: '#ffffff',
                },
                receivedMessage: {
                    bg: '#012964',
                    text: '#ffffff',
                },
                userInput: {
                    bg: '#eaeaea',
                    text: '#565867',
                },
                uniquIdentity: null,
            },
            alwaysScrollToBottom: false,
            messageStyling: true,
        };
    },
    watch: {
        channel: {
            immediate: true,
            handler(v) {
                if (v) {
                    this.visible = false;
                } else {
                    this.visible = true;
                }
            },
        },
    },
    methods: {
        handleOnType() {
            console.log('Emit typing event');
        },
        async openChat() {
            this.newMessagesCount = 1;
            this.visible = true;
            try {
                this.tokenGenerator();
                await this.$store.dispatch('chatBot/createClient').then(() => {
                  this.$store.getters['chatBot/createClient'];
                  this.isChatOpen = true;
                  this.messageList = Object.assign(this.intialMessageList);
                  this.$store.dispatch('chatBot/updateMessage', this.messageList[0]);
                  this.$store.dispatch('chatBot/updateMessage', this.messageList[1]);
                });
            } catch (e) {
                console.log('Error on authenticating Bot', e);
                this.isChatOpen = false;
            }
        },
        
         async tokenGenerator() {
            await this.$store.dispatch('chatBot/tokenGenerator').then(identity => {
              console.log('Token Generated');
              this.uniquIdentity = identity;
          });
    },

        async onMessageWasSent(msgData) {
            if (msgData.data.text.length > 0) {
                await this.$store.dispatch('chatBot/sendMessage', msgData.data.text).then(() => {
                    const messages = this.$store.getters['chatBot/chatMessage'];
                    console.log('messages', messages);
                    this.messageList = Object.assign(messages);
                });
            }
        },
        async closeChat() {
             await this.$store.dispatch('chatBot/clear').then(() => {
            this.isChatOpen = false;
            this.messageList = Object.assign([]);
            this.messageList = this.intialMessageList;
      });
        },
    },
};
</script>

<style scoped lang="stylus">
.chatBotCard
  .chat-bot-header
    font-family Titillium
    text-align center
    font-size 20px
    font-weight bold
    display flex
    flex-flow column

    &__icon
      line-height 55px
      width 72px
      height 40px
      float left

    &__content
      font-family Titillium
      font-size 24px

  /deep/.sc-launcher
    border-radius 40px
    border-bottom-right-radius 0
    box-shadow rgba(0, 0, 0, 0.3) 0px 4px 8px 0px

  /deep/.sc-launcher.opened
    border-radius 40px
    border-top-right-radius 0
    box-shadow rgba(0, 0, 0, 0.3) 0px 4px 8px 0px

  /deep/.system .sc-message--system
    background-color black !important

  /deep/.sc-chat-window.opened
    width 405px
    height calc(100% - 120px)
    max-height 560px
    box-shadow rgba(0, 0, 0, 0.3) 0px 4px 8px 0px

  /deep/.received .sc-message--text
    height auto
    padding-left 25px
    padding-right 20px
    border-radius 40px
    border-bottom-left-radius 0
    box-shadow rgba(0, 0, 0, 0.3) 0px 4px 8px 0px
    color #fff
    line-height 22px
    overflow hidden

  /deep/.sent .sc-message--text
    height auto
    padding-left 25px
    padding-right 20px
    border-radius 40px
    border-bottom-right-radius 0
    box-shadow rgba(2, 4, 2, 0.3) 1px 8px 13px 5px
    color #fff
    line-height 22px
    overflow hidden
    
</style>