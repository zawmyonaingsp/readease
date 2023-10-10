package com.sevenpeaks.zawmyonaing.readease.utils.kotlin

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Date

object DateSerializer : KSerializer<Date> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.format(DateTimeFormat.server_date_time))
    }

    override fun deserialize(decoder: Decoder): Date {
        return decoder.decodeString().asDate(DateTimeFormat.server_date_time)
            ?: throw SerializationException("Invalid date format")
    }
}