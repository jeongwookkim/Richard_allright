<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'
  import { mapActions } from 'vuex'

  export default {
    name: 'Logout',

    metaInfo: { title: 'logout' },

    extends: View,

    mixins: [
      LoadSections([
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'logout',
      },
    },

    created () {
      this.logout()
    },

    methods: {
      ...mapActions(['logout']),
    },

  }
</script>
