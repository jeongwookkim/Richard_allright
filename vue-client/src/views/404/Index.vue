<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'FourOhFour',

    metaInfo: { title: 'Not found' },

    extends: View,

    mixins: [
      LoadSections([
        '404',
        'info-alt',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'parkingLot',
      },
    },
  }
</script>
