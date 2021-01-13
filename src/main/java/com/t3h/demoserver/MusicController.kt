package com.t3h.demoserver

import com.t3h.demoserver.model.MusicInfo
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MusicController {

    @GetMapping("/api/getMusics")
    fun findMusic(
            @RequestParam(value = "musicName", defaultValue = "") musicName: String
    ): Any {
        var es: Elements

        if (!musicName.equals("")) {
            var link = "https://chiasenhac.vn/tim-kiem?q=" +
                    musicName.replace(" ", "+")
            val doc = Jsoup.connect(link).get()
            es = doc.select("div.tab-content").first()
                    .select("ul.list-unstyled")
                    .get(1)
                    .select("li.media")

        } else {
            var link = "https://chiasenhac.vn/nhac-hot.html"
            val doc = Jsoup.connect(link).get()
            es = doc.select("div.tab-content").get(2).select("li.media")
        }


        val listMusic = mutableListOf<MusicInfo>()
        for (element in es) {
            try {
                val linkImage = element.select("img").first().attr("src")
                val songName = element.select("a").attr("title")
                val artistName = element.select("div.author").text()
                val linkHtml = element.select("li.list-inline-item")
                        .select("a").attr("href")
                val numberPlayStt = element.select("small.time_stt").text()

                val numberPlay: Int
                if (numberPlayStt.contains("play_arrow ", true)) {
                    numberPlay = numberPlayStt.substring("play_arrow ".length).replace(",", "")
                            .toInt()
                } else {
                    numberPlay = numberPlayStt.replace(",", "").toInt()
                }
                listMusic.add(
                        MusicInfo(linkImage, songName, artistName,
                                linkHtml, numberPlay)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return listMusic
    }
}