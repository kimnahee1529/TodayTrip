package com.twoday.todaytrip.tourData


import android.os.Parcelable
import android.util.Log
import com.twoday.todaytrip.tourApi.AreaBasedListItem
import com.twoday.todaytrip.tourApi.IntroDetailItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.Serializable

@Serializable
sealed interface TourItem : Parcelable {
    var isAdded: Boolean
    val tourItemInfo: AreaBasedListItem
    fun getTitle() = tourItemInfo.title
    fun getAddress() = tourItemInfo.address ?: "주소 정보 없음"
    fun getImage() = tourItemInfo.firstImage ?: null
    fun getThumbnailImage() = tourItemInfo.firstImageThumbnail ?: null
    fun getLongitude() = tourItemInfo.mapX
    fun getLatitude() = tourItemInfo.mapY
    fun getTimeInfoWithLabel(): List<Pair<String, String>>
    fun getDetailInfoWithLabel(): List<Pair<String, String>>
    fun getContentId() = tourItemInfo.contentId
    fun getContentTypeId() = tourItemInfo.contentTypeId

    private fun getInfoWithNoBlank(info: String?): String =
        if (info.isNullOrBlank()) "정보 없음" else info

    @Parcelize
    @Serializable
    class TouristDestination(
        private val _tourItemInfo: AreaBasedListItem,
        private val touristDestinationInfo: IntroDetailItem
    ) : TourItem {
        override var isAdded = false
        override val tourItemInfo = _tourItemInfo
        override fun getTimeInfoWithLabel(): List<Pair<String, String>> {
            Log.d("TouristDestination", "getTimeInfoWithLabel()")
            return listOf(
                "이용 시간" to super.getInfoWithNoBlank(touristDestinationInfo.usetime),
                "쉬는 날" to super.getInfoWithNoBlank(touristDestinationInfo.restdate)
            )
        }

        override fun getDetailInfoWithLabel(): List<Pair<String, String>> {
            val detailInfoWithLabel = mutableListOf<Pair<String, String>>()
            detailInfoWithLabel.run {
                with(touristDestinationInfo) {
                    // 필수로 표시 될 관광지 정보
                    addAll(getTimeInfoWithLabel())
                    add("개장일" to super.getInfoWithNoBlank(opendate))
                    add("주차 시설" to super.getInfoWithNoBlank(parking))
                    // 있으면 표시, 없으면 표시되지 않을 관광지 정보
                    if (!accomcount.isNullOrBlank()) add("수용 인원" to accomcount)
                    if (!chkbabycarriage.isNullOrBlank()) add("유모차 대여" to chkbabycarriage)
                    if (!chkcreditcard.isNullOrBlank()) add("신용카드 가능 여부" to chkcreditcard)
                    if (!chkpet.isNullOrBlank()) add("반려동물 동반 가능 여부" to chkpet)
                    if (!expagerange.isNullOrBlank()) add("체험 가능 연령" to expagerange)
                    if (!expguide.isNullOrBlank()) add("체험 안내" to expguide)
                    if ((!heritage1.isNullOrBlank())&&(heritage1 != "0")) add("세계 문화 유산" to heritage1)
                    if ((!heritage2.isNullOrBlank())&&(heritage2 != "0")) add("세계 자연 유산" to heritage2)
                    if ((!heritage3.isNullOrBlank())&&(heritage3 != "0")) add("세계 기록 유산" to heritage3)
                    if (!infocenter.isNullOrBlank()) add("문의 및 안내" to infocenter)
                }
            }
            return detailInfoWithLabel.toList()
        }
    }

    @Parcelize
    @Serializable
    class CulturalFacilities(
        private val _tourItemInfo: @RawValue AreaBasedListItem,
        private val culturalFacilitiesInfo: @RawValue IntroDetailItem
    ) : TourItem {
        override var isAdded = false
        override val tourItemInfo = _tourItemInfo
        override fun getTimeInfoWithLabel(): List<Pair<String, String>> {
            Log.d("CulturalFacilities", "getTimeInfoWithLabel()")
            return listOf(
                "이용 시간" to super.getInfoWithNoBlank(culturalFacilitiesInfo.usetimeculture),
                "쉬는 날" to super.getInfoWithNoBlank(culturalFacilitiesInfo.restdateculture)
            )
        }

        override fun getDetailInfoWithLabel(): List<Pair<String, String>> {
            val detailInfoWithLabel = mutableListOf<Pair<String, String>>()
            detailInfoWithLabel.run {
                with(culturalFacilitiesInfo) {
                    // 필수로 표시 될 문화시설 정보
                    addAll(getTimeInfoWithLabel())
                    add("이용 요금" to super.getInfoWithNoBlank(usefee))
                    add("주차 시설" to super.getInfoWithNoBlank(parkingculture))
                    // 있으면 표시, 없으면 표시되지 않을 문화시설 정보
                    if (!parkingfee.isNullOrBlank()) add("주차 요금" to parkingfee)
                    if (!accomcountculture.isNullOrBlank()) add("수용 인원" to accomcountculture)
                    if (!chkbabycarriageculture.isNullOrBlank()) add("유모차 대여" to chkbabycarriageculture)
                    if (!chkcreditcardculture.isNullOrBlank()) add("신용카드 가능 여부" to chkcreditcardculture)
                    if (!chkpetculture.isNullOrBlank()) add("반려동물 동반 가능 여부" to chkpetculture)
                    if (!discountinfo.isNullOrBlank()) add("할인 정보" to discountinfo)
                    if (!scale.isNullOrBlank()) add("규모" to scale)
                    if (!spendtime.isNullOrBlank()) add("관람 소요 시간" to spendtime)
                    if (!infocenterculture.isNullOrBlank()) add("문의 및 안내" to infocenterculture)
                }
            }
            return detailInfoWithLabel.toList()
        }
    }

    @Parcelize
    @Serializable
    class Restaurant(
        private val _tourItemInfo: @RawValue AreaBasedListItem,
        private val restaurantInfo: @RawValue IntroDetailItem
    ) : TourItem {
        override var isAdded = false
        override val tourItemInfo = _tourItemInfo
        override fun getTimeInfoWithLabel(): List<Pair<String, String>> =
            listOf(
                "이용 시간" to super.getInfoWithNoBlank(restaurantInfo.opentimefood),
                "쉬는 날" to super.getInfoWithNoBlank(restaurantInfo.restdatefood)
            )

        override fun getDetailInfoWithLabel(): List<Pair<String, String>> {
            val infoWithLabel = mutableListOf<Pair<String, String>>()
            infoWithLabel.run {
                with(restaurantInfo) {
                    // 필수로 표시 될 문화시설 정보
                    addAll(getTimeInfoWithLabel())
                    add("주차 시설" to super.getInfoWithNoBlank(parkingfood))
                    add("대표 메뉴" to super.getInfoWithNoBlank(firstmenu))
                    // 있으면 표시, 없으면 표시되지 않을 문화시설 정보
                    if (!treatmenu.isNullOrBlank()) add("취급 메뉴" to treatmenu)
                    if (!packing.isNullOrBlank()) add("포장 가능" to packing)
                    if (!chkcreditcardfood.isNullOrBlank()) add("신용카드 가능 여부" to chkcreditcardfood)
                    if (!discountinfofood.isNullOrBlank()) add("할인 정보" to discountinfofood)
                    if (!scalefood.isNullOrBlank()) add("규모" to scalefood)
                    if (!seat.isNullOrBlank()) add("좌석 수" to seat)
                    if (!smoking.isNullOrBlank()) add("금연/흡연 여부" to smoking)
                    if (!kidsfacility.isNullOrBlank()) add("어린이 놀이방" to kidsfacility)
                    if (!opendatefood.isNullOrBlank()) add("개업일" to opendatefood)
                    if (!lcnsno.isNullOrBlank()) add("인허가번호" to lcnsno)
                    if (!infocenterfood.isNullOrBlank()) add("문의 및 안내" to infocenterfood)
                    if (!reservationfood.isNullOrBlank()) add("예약 안내" to reservationfood)
                }
            }
            return infoWithLabel.toList()
        }
    }

    @Parcelize
    @Serializable
    class LeisureSports(
        private val _tourItemInfo: @RawValue AreaBasedListItem,
        private val leisureSportsInfo: @RawValue IntroDetailItem
    ) : TourItem {
        override var isAdded = false
        override val tourItemInfo = _tourItemInfo
        override fun getTimeInfoWithLabel(): List<Pair<String, String>> =
            listOf(
                "이용 시간" to super.getInfoWithNoBlank(leisureSportsInfo.usetimeleports),
                "쉬는 날" to super.getInfoWithNoBlank(leisureSportsInfo.restdateleports)
            )

        override fun getDetailInfoWithLabel(): List<Pair<String, String>> {
            val infoWithLabel = mutableListOf<Pair<String, String>>()
            infoWithLabel.run {
                with(leisureSportsInfo) {
                    // 필수로 표시 될 레포츠 정보
                    addAll(getTimeInfoWithLabel())
                    add("입장료" to super.getInfoWithNoBlank(usefeeleports))
                    add("개장 기간" to super.getInfoWithNoBlank(openperiod))
                    add("주차 시설" to super.getInfoWithNoBlank(parking))
                    // 있으면 표시, 없으면 표시되지 않을 레포츠 정보
                    if (!parkingfeeleports.isNullOrBlank()) add("주차 요금" to parkingfeeleports)
                    if (!expagerangeleports.isNullOrBlank()) add("체험 가능 연령" to expagerangeleports)
                    if (!reservation.isNullOrBlank()) add("예약 안내" to reservation)
                    if (!scaleleports.isNullOrBlank()) add("규모" to scaleleports)
                    if (!accomcountleports.isNullOrBlank()) add("수용 인원" to accomcountleports)
                    if (!chkbabycarriageleports.isNullOrBlank()) add("유모차 대여" to chkbabycarriageleports)
                    if (!chkcreditcardleports.isNullOrBlank()) add("신용카드 가능 여부" to chkcreditcardleports)
                    if (!chkpetleports.isNullOrBlank()) add("반려동물 동반 가능 여부" to chkpetleports)
                    if (!infocenterleports.isNullOrBlank()) add("문의 및 안내" to infocenterleports)
                }
            }
            return infoWithLabel.toList()
        }
    }

    @Parcelize
    @Serializable
    class EventPerformanceFestival(
        private val _tourItemInfo: @RawValue AreaBasedListItem,
        private val eventPerformanceFestivalInfo: @RawValue IntroDetailItem
    ) : TourItem {
        override var isAdded = false
        override val tourItemInfo = _tourItemInfo
        override fun getTimeInfoWithLabel(): List<Pair<String, String>> =
            mutableListOf<Pair<String, String>>().apply {
                add("행사 시간" to super.getInfoWithNoBlank(eventPerformanceFestivalInfo.playtime))
                val eventStartDate =
                    super.getInfoWithNoBlank(eventPerformanceFestivalInfo.eventstartdate)
                val eventEndDate =
                    super.getInfoWithNoBlank(eventPerformanceFestivalInfo.eventenddate)
                add("행사 기간" to "${eventStartDate}~${eventEndDate}")
            }.toList()


        override fun getDetailInfoWithLabel(): List<Pair<String, String>> {
            val infoWithLabel = mutableListOf<Pair<String, String>>()
            infoWithLabel.run {
                with(eventPerformanceFestivalInfo) {
                    // 필수로 표시 될 행사/공연/축제 정보
                    addAll(getTimeInfoWithLabel())
                    add(("이용 요금" to (usefee ?: "정보 없음")) as Pair<String, String>)
                    // 있으면 표시, 없으면 표시되지 않을 행사/공연/축제 정보
                    if (!agelimit.isNullOrBlank()) add("관람 가능 연령" to agelimit)
                    if (!bookingplace.isNullOrBlank()) add("예매처" to bookingplace)
                    if (!discountinfofestival.isNullOrBlank()) add("할인 정보" to discountinfofestival)
                    if (!eventstartdate.isNullOrBlank()) add("행사 시작일" to eventstartdate)
                    if (!eventenddate.isNullOrBlank()) add("행사 종료일" to eventenddate)
                    if (!eventplace.isNullOrBlank()) add("행사 장소" to eventplace)
                    if (!placeinfo.isNullOrBlank()) add("행사장 위치 안내" to placeinfo)
                    if (!eventhomepage.isNullOrBlank()) add("행사 홈페이지" to eventhomepage)
                    if (!program.isNullOrBlank()) add("행사 프로그램" to program)
                    if (!subevent.isNullOrBlank()) add("부대 행사" to subevent)
                    if (!festivalgrade.isNullOrBlank()) add("축제 등급" to festivalgrade)
                    if (!playtime.isNullOrBlank()) add("공연 시간" to playtime)
                    if (!spendtimefestival.isNullOrBlank()) add("관람 소요 시간" to spendtimefestival)
                    if (!sponsor1.isNullOrBlank()) add("주최자 정보" to sponsor1)
                    if (!sponsor1tel.isNullOrBlank()) add("주최자 연락처" to sponsor1tel)
                    if (!sponsor2.isNullOrBlank()) add("주관사 정보" to sponsor2)
                    if (!sponsor2tel.isNullOrBlank()) add("주관사 연락처" to sponsor2tel)
                }
            }
            return infoWithLabel.toList()
        }
    }
}
