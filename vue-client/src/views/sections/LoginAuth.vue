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
  import firebase from '../../../firebase/init'
  import 'firebaseui/dist/firebaseui.css'
  import * as firebaseui from 'firebaseui'

  const ui = new firebaseui.auth.AuthUI(firebase.auth())

  export default {
    name: 'LoginAuth',
    mounted () {
      this.initUI()
    },
    methods: {
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
              firebase.auth().getRedirectResult().then(function (result) {
                console.log("result는")
                console.log(result)
                if (result.credential) {
                  // This gives you a Google Access Token. You can use it to access the Google API.
                  var token = result.credential.accessToken
                  console.log("크리덴셜이 있을 때 result")
                  console.log(token)
                }
                // The signed-in user info.
                var user = result.user
                console.log("user는")
                console.log(user)
              }).catch(function (error) {
                var errorCode = error.code
                var errorMessage = error.message
                var email = error.email
                var credential = error.credential
                console.log(errorCode, errorMessage, email, credential)
              })
              alert(`${authResult.user.displayName}login 성공!`)
              return false
            },
          },
        })
      },
    },
  }
</script>
