package fr.gtm.project.proxibanque.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/{id}/virement")
public class TransferController {

	@RequestMapping("/interne")
	public ModelAndView internalTransfert(@PathVariable Integer id) {
		final ModelAndView mav = new ModelAndView("transfer");
		return mav;
	}
}
