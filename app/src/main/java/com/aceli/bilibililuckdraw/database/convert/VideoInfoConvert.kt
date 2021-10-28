package com.aceli.bilibililuckdraw.database.convert

import androidx.room.TypeConverter
import com.aceli.bilibililuckdraw.bean.*
import com.aceli.bilibililuckdraw.helper.GsonHelper
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class VideoInfoConvert {
    @TypeConverter
    fun objectListDescV2DTOToString(list: List<DescV2DTO>?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringListDescV2DTOToObject(json: String?): List<DescV2DTO>? {
        val listType: Type = object : TypeToken<List<DescV2DTO>?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }
    @TypeConverter
    fun objectListPagesDTOToString(list: List<PagesDTO>?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringListPagesDTOToObject(json: String?): List<PagesDTO>? {
        val listType: Type = object : TypeToken<List<PagesDTO>?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }

    @TypeConverter
    fun objectRightsDTOToString(list: RightsDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringRightsDTOToObject(json: String?): RightsDTO? {
        val listType: Type = object : TypeToken<RightsDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }

    @TypeConverter
    fun objectOwnerDTOoString(list: OwnerDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringOwnerDTOToObject(json: String?): OwnerDTO? {
        val listType: Type = object : TypeToken<OwnerDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }

    @TypeConverter
    fun objectStatDTOToString(list: StatDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringStatDTOToObject(json: String?): StatDTO? {
        val listType: Type = object : TypeToken<StatDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }

    @TypeConverter
    fun objectDimensionDTOToString(list: DimensionDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringDimensionDTOToObject(json: String?): DimensionDTO? {
        val listType: Type = object : TypeToken<DimensionDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }

    @TypeConverter
    fun objectSubtitleDTOToString(list: SubtitleDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringSubtitleDTOToObject(json: String?): SubtitleDTO? {
        val listType: Type = object : TypeToken<SubtitleDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }
    @TypeConverter
    fun objectUserGarbDTOToString(list: UserGarbDTO?): String? {
        return GsonHelper.instance.gson.toJson(list)
    }

    @TypeConverter
    fun stringUserGarbDTOToObject(json: String?): UserGarbDTO? {
        val listType: Type = object : TypeToken<UserGarbDTO?>() {}.type
        return GsonHelper.instance.gson.fromJson(json, listType)
    }
}