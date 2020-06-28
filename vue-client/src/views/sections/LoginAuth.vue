<template>
  <base-section
    id="loginAuth"
  >
    <base-section-heading title="Login Authentication">
      auth 버튼 추가하기
      <div id="firebaseui-auth-container" />
    </base-section-heading>
  </base-section>
</template>

<script>
  import firebase from '../../../firebase/init'
  import firebaseui from 'firebaseui'
  import 'firebaseui/dist/firebaseui.css'

  const auth = firebase.auth()
  const ui = new firebaseui.auth.AuthUI(auth)

  export default {
    name: 'LoginAuth',
    mounted () {
      this.initUI()
      // // 현재 로그인한 회원의 정보를 알 수 있는 함수이다. 존재하면 딕셔너리가, 아니면 null값이 나온다.
      // auth.onAuthStateChanged((user) => {
      //   if (user) {
      //     alert('이미 로그인 한 사용자입니다!')
      //   }
      //   // 현재 유저가 존재하지 않으면 로그인창을 보여준다.
      //   this.initUI()
      // })
    },
    methods: {
      signIn: function () {
        const provider = new firebase.auth.GoogleAuthProvider()
        auth.signInWithPopup(provider)
      },
      initUI: function () {
        // template에 존재하는 div에 ui.start 명령어를 사용하면 firebaseui가 알아서 그려준다.
        ui.start('#firebaseui-auth-container', {
          // 현재 사용하는 옵션은 이메일 로그인만 사용한다.
          signInOptions: [
            firebase.auth.FacebookAuthProvider.PROVIDER_ID,
            firebase.auth.GoogleAuthProvider.PROVIDER_ID,
            firebase.auth.EmailAuthProvider.PROVIDER_ID,
          ],
          callbacks: {
            // 로그인이 성공하면,
            signInSuccessWithAuthResult: (authResult, redirectUrl) => {
              // 로그인 정보를 각각의 data에 저장한다.
              alert(`${authResult.user.displayName}login 성공!`)
              return false
            },
          },
        })
      },
    },
  }
</script>
