<template>
  <base-section id="approve">
    <base-heading title="결제완료" />

    <base-subheading
      space="8"
      title="결제가 완료되었습니다."
    />

    <div class="text-center">
      <base-btn :to="{ name: 'Home' }">
        홈으로 돌아가기
      </base-btn>
    </div>
  </base-section>
</template>

<script>
  import qs from 'qs'
  import axios from 'axios'
  import { mapGetters } from 'vuex'
  import cookies from 'vue-cookies'
  import firebase from '../../../firebase/init'

  export default {
    name: 'Approve',

    provide: {
      heading: { align: 'center' },
    },

    computed: {
      ...mapGetters(['tid']),
    },

    created () {
      console.log(this.tid)
      const data = {
        pg_token: qs.parse(location.search.replace('?', '')),
        tid: cookies.get('tid'),
      }

      axios.post('http://localhost:3030/approve', data, { withCredentials: true })
        .then(response => {
          cookies.remove('tid')
          firebase
            .firestore()
            .collection('usedParkingLot')
            .add({
              uid: cookies.get('user_uid'),
              orderNumber: response.data.aid,
              parkingLotName: response.data.item_name,
              timestamp: response.data.approved_at,
            })
            .catch((error) => {
              console.error('usedParkingLot 정보 저장 에러', error)
            })
        })
        .catch((err) => {
          console.log(err)
        })
    },
  }
</script>
