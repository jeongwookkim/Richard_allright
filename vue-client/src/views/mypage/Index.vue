<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Mypage',

    metaInfo: { title: 'mypage' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'mypage',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'mypage',
      },
    },
  }
</script>
