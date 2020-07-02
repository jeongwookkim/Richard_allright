<template>
  <base-section
    id="loginAuth"
  >
    <base-section-heading title="Login Authentication">
      <div id="firebaseui-auth-container" />
    </base-section-heading>
  </base-section>
</template>

<script>
  import { mapActions } from 'vuex'
  import * as firebaseui from 'firebaseui'
  import firebase from '../../../firebase/init'
  import 'firebaseui/dist/firebaseui.css'

  const ui = new firebaseui.auth.AuthUI(firebase.auth())

  export default {
    name: 'LoginAuth',
    mounted () {
      this.initUI()
    },
    methods: {
      ...mapActions(['login']),
      signIn: function () {
        const provider = new firebase.auth.GoogleAuthProvider()
        firebase.auth.signInWithPopup(provider)
      },
      initUI: function () {
        ui.start('#firebaseui-auth-container', {
          signInOptions: [
            firebase.auth.FacebookAuthProvider.PROVIDER_ID,
            firebase.auth.GoogleAuthProvider.PROVIDER_ID,
            firebase.auth.EmailAuthProvider.PROVIDER_ID,
          ],
          credentialHelper: firebaseui.auth.CredentialHelper.GOOGLE_YOLO,
          callbacks: {
            signInSuccessWithAuthResult: (authResult, redirectUrl) => {
              firebase
                .firestore()
                .collection('users')
                .add({
                  uid: authResult.user.uid,
                  profilePicUrl: authResult.user.photoURL,
                  timestamp: new Date(),
                })
                .catch((error) => {
                  console.error('user 정보 저장 에러', error)
                })
              const user = authResult.user
              this.login(user)
            },
          },
        })
      },
    },
  }
</script>
