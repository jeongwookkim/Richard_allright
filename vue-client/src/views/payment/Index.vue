<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Payment',

    metaInfo: { title: 'Payment History' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'confirmPay',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'payment',
      },
    },
  }
</script>
