package org.t246osslab.easybuggy4kt.troubles

import java.util.Locale

import javax.servlet.http.HttpServletRequest

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.text.WordUtils
import org.owasp.esapi.ESAPI
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.t246osslab.easybuggy4kt.controller.AbstractController

@Controller
class MojibakeController : AbstractController() {

    @RequestMapping(value = "/mojibake")
    fun process(@RequestParam(value = "string", required = false) string: String?, mav: ModelAndView,
                req: HttpServletRequest, locale: Locale): ModelAndView {
        setViewAndCommonObjects(mav, locale, "mojibake")
        if (!StringUtils.isBlank(string)) {
            // Capitalize the given string
            val capitalizedName = WordUtils.capitalize(string)
            mav.addObject("msg", msg?.getMessage("label.capitalized.string", null, locale) + " : "
                    + ESAPI.encoder().encodeForHTML(capitalizedName))
        } else {
            mav.addObject("msg", msg?.getMessage("msg.enter.string", null, locale))
        }
        return mav
    }
}