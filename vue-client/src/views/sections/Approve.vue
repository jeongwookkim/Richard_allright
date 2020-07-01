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

  export default {
    name: 'Approve',

    provide: {
      heading: { align: 'center' },
    },

    computed: {
      ...mapGetters(['paymentInfo']),
    },

    created () {
      const token = qs.parse(location.search.replace('?', ''))

      axios.post('http://localhost:3030/approve', { token }, { withCredentials: true })
        .then(response => {
          console.log(response.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
  }
</script>
