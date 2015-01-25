package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.map.Museum;
import com.neuralnoise.map.service.map.MuseumService;
import com.neuralnoise.map.web.map.util.AbstractContributedEntityController;

@Controller
@RequestMapping("/museum")
public class MuseumController extends AbstractContributedEntityController<Museum, MuseumService> {

	private static final Logger log = LoggerFactory.getLogger(MuseumController.class);

}
