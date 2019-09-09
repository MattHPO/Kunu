package com.taipei.yanghaobo.kunu.db

class GoogleBookGson {
  /**
   * kind : books#volumes
   * totalItems : 495
   * items : [{"kind":"books#volume","id":"bgZwjdB4xgEC","etag":"5HwAY5neK3w","selfLink":"https://www.googleapis.com/books/v1/volumes/bgZwjdB4xgEC","volumeInfo":{"title":"The Genetics of the Dog","authors":["Anatoly Ruvinsky","J. Sampson"],"publisher":"CABI","publishedDate":"2001","description":"This comprehensive reference book contains the latest research and information on dog genetics. No similar book is currently available - this is the first high level research work on the topic.Written by the leading authorities in the field from Europe, the USA, Russia and Australia","industryIdentifiers":[{"type":"ISBN_10","identifier":"0851990789"},{"type":"ISBN_13","identifier":"9780851990781"}],"readingModes":{"text":false,"image":true},"pageCount":564,"printType":"BOOK","categories":["Pets"],"averageRating":5,"ratingsCount":2,"maturityRating":"NOT_MATURE","allowAnonLogging":false,"contentVersion":"preview-1.0.0","panelizationSummary":{"containsEpubBubbles":false,"containsImageBubbles":false},"imageLinks":{"smallThumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"},"language":"en","previewLink":"http://books.google.com.tw/books?id=bgZwjdB4xgEC&printsec=frontcover&dq=dog&hl=&as_pt=BOOKS&cd=1&source=gbs_api","infoLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC&source=gbs_api","canonicalVolumeLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC"},"saleInfo":{"country":"TW","saleability":"FOR_SALE","isEbook":true,"listPrice":{"amount":4686,"currencyCode":"TWD"},"retailPrice":{"amount":3280,"currencyCode":"TWD"},"buyLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC&rdid=book-bgZwjdB4xgEC&rdot=1&source=gbs_api","offers":[{"finskyOfferType":1,"listPrice":{"amountInMicros":4.686E9,"currencyCode":"TWD"},"retailPrice":{"amountInMicros":3.28E9,"currencyCode":"TWD"}}]},"accessInfo":{"country":"TW","viewability":"PARTIAL","embeddable":true,"publicDomain":false,"textToSpeechPermission":"ALLOWED","epub":{"isAvailable":false},"pdf":{"isAvailable":true,"acsTokenLink":"http://books.google.com.tw/books/download/The_Genetics_of_the_Dog-sample-pdf.acsm?id=bgZwjdB4xgEC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"},"webReaderLink":"http://play.google.com/books/reader?id=bgZwjdB4xgEC&hl=&as_pt=BOOKS&printsec=frontcover&source=gbs_api","accessViewStatus":"SAMPLE","quoteSharingAllowed":false},"searchInfo":{"textSnippet":"This comprehensive reference book contains the latest research and information on dog genetics."}}]
   */

  var kind: String? = null
  var totalItems: Int = 0
  var items: List<ItemsBean>? = null

  class ItemsBean {

    /**
     * kind : books#volume
     * id : bgZwjdB4xgEC
     * etag : 5HwAY5neK3w
     * selfLink : https://www.googleapis.com/books/v1/volumes/bgZwjdB4xgEC
     * volumeInfo : {"title":"The Genetics of the Dog","authors":["Anatoly Ruvinsky","J. Sampson"],"publisher":"CABI","publishedDate":"2001","description":"This comprehensive reference book contains the latest research and information on dog genetics. No similar book is currently available - this is the first high level research work on the topic.Written by the leading authorities in the field from Europe, the USA, Russia and Australia","industryIdentifiers":[{"type":"ISBN_10","identifier":"0851990789"},{"type":"ISBN_13","identifier":"9780851990781"}],"readingModes":{"text":false,"image":true},"pageCount":564,"printType":"BOOK","categories":["Pets"],"averageRating":5,"ratingsCount":2,"maturityRating":"NOT_MATURE","allowAnonLogging":false,"contentVersion":"preview-1.0.0","panelizationSummary":{"containsEpubBubbles":false,"containsImageBubbles":false},"imageLinks":{"smallThumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"},"language":"en","previewLink":"http://books.google.com.tw/books?id=bgZwjdB4xgEC&printsec=frontcover&dq=dog&hl=&as_pt=BOOKS&cd=1&source=gbs_api","infoLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC&source=gbs_api","canonicalVolumeLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC"}
     * saleInfo : {"country":"TW","saleability":"FOR_SALE","isEbook":true,"listPrice":{"amount":4686,"currencyCode":"TWD"},"retailPrice":{"amount":3280,"currencyCode":"TWD"},"buyLink":"https://play.google.com/store/books/details?id=bgZwjdB4xgEC&rdid=book-bgZwjdB4xgEC&rdot=1&source=gbs_api","offers":[{"finskyOfferType":1,"listPrice":{"amountInMicros":4.686E9,"currencyCode":"TWD"},"retailPrice":{"amountInMicros":3.28E9,"currencyCode":"TWD"}}]}
     * accessInfo : {"country":"TW","viewability":"PARTIAL","embeddable":true,"publicDomain":false,"textToSpeechPermission":"ALLOWED","epub":{"isAvailable":false},"pdf":{"isAvailable":true,"acsTokenLink":"http://books.google.com.tw/books/download/The_Genetics_of_the_Dog-sample-pdf.acsm?id=bgZwjdB4xgEC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"},"webReaderLink":"http://play.google.com/books/reader?id=bgZwjdB4xgEC&hl=&as_pt=BOOKS&printsec=frontcover&source=gbs_api","accessViewStatus":"SAMPLE","quoteSharingAllowed":false}
     * searchInfo : {"textSnippet":"This comprehensive reference book contains the latest research and information on dog genetics."}
     */

    var kind: String? = null
    var id: String? = null
    var etag: String? = null
    var selfLink: String? = null
    var volumeInfo: VolumeInfoBean? = null
    var saleInfo: SaleInfoBean? = null
    var accessInfo: AccessInfoBean? = null
    var searchInfo: SearchInfoBean? = null

    class VolumeInfoBean {

      /**
       * title : The Genetics of the Dog
       * authors : ["Anatoly Ruvinsky","J. Sampson"]
       * publisher : CABI
       * publishedDate : 2001
       * description : This comprehensive reference book contains the latest research and information on dog genetics. No similar book is currently available - this is the first high level research work on the topic.Written by the leading authorities in the field from Europe, the USA, Russia and Australia
       * industryIdentifiers : [{"type":"ISBN_10","identifier":"0851990789"},{"type":"ISBN_13","identifier":"9780851990781"}]
       * readingModes : {"text":false,"image":true}
       * pageCount : 564
       * printType : BOOK
       * categories : ["Pets"]
       * averageRating : 5.0
       * ratingsCount : 2
       * maturityRating : NOT_MATURE
       * allowAnonLogging : false
       * contentVersion : preview-1.0.0
       * panelizationSummary : {"containsEpubBubbles":false,"containsImageBubbles":false}
       * imageLinks : {"smallThumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"}
       * language : en
       * previewLink : http://books.google.com.tw/books?id=bgZwjdB4xgEC&printsec=frontcover&dq=dog&hl=&as_pt=BOOKS&cd=1&source=gbs_api
       * infoLink : https://play.google.com/store/books/details?id=bgZwjdB4xgEC&source=gbs_api
       * canonicalVolumeLink : https://play.google.com/store/books/details?id=bgZwjdB4xgEC
       */

      var title: String? = null
      var publisher: String? = null
      var publishedDate: String? = null
      var description: String? = null
      var readingModes: ReadingModesBean? = null
      var pageCount: Int = 0
      var printType: String? = null
      var averageRating: Double = 0.toDouble()
      var ratingsCount: Int = 0
      var maturityRating: String? = null
      var isAllowAnonLogging: Boolean = false
      var contentVersion: String? = null
      var panelizationSummary: PanelizationSummaryBean? = null
      var imageLinks: ImageLinksBean? = null
      var language: String? = null
      var previewLink: String? = null
      var infoLink: String? = null
      var canonicalVolumeLink: String? = null
      var authors: List<String>? = null
      var industryIdentifiers: List<IndustryIdentifiersBean>? = null
      var categories: List<String>? = null

      class ReadingModesBean {

        /**
         * text : false
         * image : true
         */

        var isText: Boolean = false
        var isImage: Boolean = false
      }

      class PanelizationSummaryBean {

        /**
         * containsEpubBubbles : false
         * containsImageBubbles : false
         */

        var isContainsEpubBubbles: Boolean = false
        var isContainsImageBubbles: Boolean = false
      }

      class ImageLinksBean {

        /**
         * smallThumbnail : http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api
         * thumbnail : http://books.google.com/books/content?id=bgZwjdB4xgEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api
         */

        var smallThumbnail: String? = null
        var thumbnail: String? = null
      }

      class IndustryIdentifiersBean {

        /**
         * type : ISBN_10
         * identifier : 0851990789
         */

        var type: String? = null
        var identifier: String? = null
      }
    }

    class SaleInfoBean {

      /**
       * country : TW
       * saleability : FOR_SALE
       * isEbook : true
       * listPrice : {"amount":4686,"currencyCode":"TWD"}
       * retailPrice : {"amount":3280,"currencyCode":"TWD"}
       * buyLink : https://play.google.com/store/books/details?id=bgZwjdB4xgEC&rdid=book-bgZwjdB4xgEC&rdot=1&source=gbs_api
       * offers : [{"finskyOfferType":1,"listPrice":{"amountInMicros":4.686E9,"currencyCode":"TWD"},"retailPrice":{"amountInMicros":3.28E9,"currencyCode":"TWD"}}]
       */

      var country: String? = null
      var saleability: String? = null
      var isIsEbook: Boolean = false
      var listPrice: ListPriceBean? = null
      var retailPrice: RetailPriceBean? = null
      var buyLink: String? = null
      var offers: List<OffersBean>? = null

      class ListPriceBean {

        /**
         * amount : 4686.0
         * currencyCode : TWD
         */

        var amount: Double = 0.toDouble()
        var currencyCode: String? = null
      }

      class RetailPriceBean {

        /**
         * amount : 3280.0
         * currencyCode : TWD
         */

        var amount: Double = 0.toDouble()
        var currencyCode: String? = null
      }

      class OffersBean {

        /**
         * finskyOfferType : 1
         * listPrice : {"amountInMicros":4.686E9,"currencyCode":"TWD"}
         * retailPrice : {"amountInMicros":3.28E9,"currencyCode":"TWD"}
         */

        var finskyOfferType: Int = 0
        var listPrice: ListPriceBeanX? = null
        var retailPrice: RetailPriceBeanX? = null

        class ListPriceBeanX {

          /**
           * amountInMicros : 4.686E9
           * currencyCode : TWD
           */

          var amountInMicros: Double = 0.toDouble()
          var currencyCode: String? = null
        }

        class RetailPriceBeanX {

          /**
           * amountInMicros : 3.28E9
           * currencyCode : TWD
           */

          var amountInMicros: Double = 0.toDouble()
          var currencyCode: String? = null
        }
      }
    }

    class AccessInfoBean {

      /**
       * country : TW
       * viewability : PARTIAL
       * embeddable : true
       * publicDomain : false
       * textToSpeechPermission : ALLOWED
       * epub : {"isAvailable":false}
       * pdf : {"isAvailable":true,"acsTokenLink":"http://books.google.com.tw/books/download/The_Genetics_of_the_Dog-sample-pdf.acsm?id=bgZwjdB4xgEC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"}
       * webReaderLink : http://play.google.com/books/reader?id=bgZwjdB4xgEC&hl=&as_pt=BOOKS&printsec=frontcover&source=gbs_api
       * accessViewStatus : SAMPLE
       * quoteSharingAllowed : false
       */

      var country: String? = null
      var viewability: String? = null
      var isEmbeddable: Boolean = false
      var isPublicDomain: Boolean = false
      var textToSpeechPermission: String? = null
      var epub: EpubBean? = null
      var pdf: PdfBean? = null
      var webReaderLink: String? = null
      var accessViewStatus: String? = null
      var isQuoteSharingAllowed: Boolean = false

      class EpubBean {

        /**
         * isAvailable : false
         */

        var isIsAvailable: Boolean = false
      }

      class PdfBean {

        /**
         * isAvailable : true
         * acsTokenLink : http://books.google.com.tw/books/download/The_Genetics_of_the_Dog-sample-pdf.acsm?id=bgZwjdB4xgEC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api
         */

        var isIsAvailable: Boolean = false
        var acsTokenLink: String? = null
      }
    }

    class SearchInfoBean {

      /**
       * textSnippet : This comprehensive reference book contains the latest research and information on dog genetics.
       */

      var textSnippet: String? = null
    }
  }

  override fun toString(): String {
    return "GoogleBookGson{" +
        "kind='" + kind + '\''.toString() +
        ", totalItems=" + totalItems +
        ", items=" + items +
        '}'.toString()
  }
}
