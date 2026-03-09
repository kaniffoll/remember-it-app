package com.kaniffoll.rememberit.domain.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ModeSerializer : KSerializer<Mode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(SERIAL_NAME, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Mode) {
        val str = when (value) {
            is Mode.Intensive -> INTENSIVE_AS_STRING
            is Mode.Normal -> NORMAL_AS_STRING
        }
        encoder.encodeString(str)
    }

    override fun deserialize(decoder: Decoder): Mode {
        return when (val str = decoder.decodeString()) {
            NORMAL_AS_STRING -> Mode.Normal
            INTENSIVE_AS_STRING -> Mode.Intensive
            else -> throw SerializationException("Unknown type $str")
        }
    }

    companion object {
        private const val SERIAL_NAME = "MODE"
        private const val NORMAL_AS_STRING = "Normal"
        private const val INTENSIVE_AS_STRING = "Intensive"
    }
}