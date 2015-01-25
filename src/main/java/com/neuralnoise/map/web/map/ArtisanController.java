package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.service.map.ArtisanService;
import com.neuralnoise.map.web.map.util.AbstractContributedEntityController;

@Controller
@RequestMapping("/artisan")
public class ArtisanController extends AbstractContributedEntityController<Artisan, ArtisanService> {

	private static final Logger log = LoggerFactory.getLogger(ArtisanController.class);

}
