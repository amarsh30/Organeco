package com.organeco.model.local.fertilizer

import com.organeco.R

object DataDummySource {
    fun getDummyDataList(): List<DataDummy> {
        return listOf(
            DataDummy(
                1,
                "Pupuk Kompos",
                "sayuran, buah-buahan, bunga, dan tanaman hias",
                "Terbuat dari bahan organik seperti sisa-sisa tumbuhan, rumput, daun, dan limbah dapur. " +
                        "Digunakan untuk meningkatkan kesuburan tanah dan menyediakan nutrisi penting.",
                R.drawable.image1),
            DataDummy(
                2,
                "Pupuk kandang",
                "tanaman sayuran, buah-buahan, tanaman bunga, dan tanaman pohon.",
                "Terbuat dari kotoran hewan seperti kotoran sapi, kambing, atau ayam. Mengandung nutrisi seperti nitrogen, fosfor, dan kalium yang diperlukan untuk pertumbuhan tanaman.",
                R.drawable.image2),
            DataDummy(
                3,
                "Pupuk daun",
                "sayuran, tanaman hias, dan tanaman buah.",
                "Pupuk yang diterapkan melalui semprotan daun. Biasanya mengandung mikronutrien penting seperti zat besi, magnesium, dan mangan.",
                R.drawable.image3),
            DataDummy(
                4,
                "Pupuk hijau",
                "sayuran, tanaman buah, dan tanaman hias.",
                "Terbuat dari bahan organik seperti sisa-sisa tumbuhan, rumput, daun, dan limbah dapur. " +
                        "Digunakan untuk meningkatkan kesuburan tanah dan menyediakan nutrisi penting.",
                R.drawable.image4),
            DataDummy(
                5,
                "Pupuk limbah tumbuhan",
                "sayuran, buah-buahan, tanaman hias, dan tanaman pangan.",
                "Limbah pertanian seperti jerami, sekam padi, atau serbuk gergaji yang diubah menjadi pupuk organik untuk memperbaiki struktur tanah.",
                R.drawable.image1),
            DataDummy(
                6,
                "Pupuk alga",
                "mikroba dan tanaman hias.",
                "Pupuk yang berasal dari alga laut, kaya akan nutrisi seperti nitrogen, fosfor, dan kalium. Dapat membantu meningkatkan pertumbuhan dan kekebalan tanaman.",
                R.drawable.image2),
            DataDummy(
                7,
                "Pupuk Tulang",
                "Buah-buahan, sayuran, bunga",
                "Terbuat dari tulang hewan yang diolah menjadi serbuk halus. Mengandung fosfor dan kalsium yang baik untuk perkembangan akar dan struktur tanaman.",
                R.drawable.image3),
            DataDummy(
                8,
                "Pupuk bokashi",
                "Tanaman di kebun, sayuran, buah-buahan, dan tanaman hias.",
                "Pupuk organik fermentasi yang dibuat dengan mencampurkan bahan organik dengan mikroorganisme menguntungkan. Meningkatkan kualitas tanah dan menyediakan nutrisi bagi tanaman.",
                R.drawable.image4),
            DataDummy(
                9,
                "Pupuk limbah organik rumah tangga",
                "sayuran, tanaman hias, dan tanaman buah.",
                "Sisa-sisa makanan, seperti kulit buah, sayuran, atau ampas kopi, yang dikomposkan dan digunakan sebagai pupuk organik untuk tanaman.",
                R.drawable.image1),
            DataDummy(
                10,
                "Pupuk hayati",
                "sayuran, buah-buahan, tanaman hias, dan tanaman pangan.",
                "Pupuk yang mengandung mikroorganisme seperti bakteri atau jamur yang membantu meningkatkan kualitas tanah dan ketersediaan nutrisi.",
                R.drawable.image2),
        )
    }

}