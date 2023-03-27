package com.breaktime.signscreen.screen.appointments.salons

import androidx.compose.ui.graphics.Color

enum class CategoryEnum(val title: String, val color: Color) {
    HAIR("HAIR", Color(0xFF44E986)),
    BARBERSHOP("BARBERSHOP", Color(0xFF52C2A9)),
    EYELASHES("EYELASHES", Color(0xFFEEDE71)),
    BROWS("BROWS", Color(0xFFF3B04D)),
    MASSAGE("MASSAGE", Color(0xFFD063E9)),
    SPA("SPA", Color(0xFF5CE3F3)),
    NAILS("NAILS", Color(0xFFE97A72)),
    SOLARIUM("SOLARIUM", Color(0xFFA9F356)),
    FACIAL_CONTOURING("FACIAL CONTOURING", Color(0xFFF145A4)),
    MAKE_UP_TATTOO("MAKE UP TATTOO", Color(0xFFEE6694)),
    COSMETOLOGY("COSMETOLOGY", Color(0xFF12E0C8)),
    EPILATION("EPILATION", Color(0xFF2845E7)),
    MAKE_UP("MAKE UP", Color(0xFFEC6A6E))
}
