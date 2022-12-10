package ru.axtane.CAHI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.CAHI.models.Essay;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EssaysService {
    private final ArrangementsService arrangementsService;
    private final ChantsService chantsService;
    private final ChoirsService choirsService;
    private final FolkProcessingService folkProcessingService;
    private final OpusDPSService opusDPSService;

    @Autowired
    public EssaysService(ArrangementsService arrangementsService, ChantsService chantsService, ChoirsService choirsService, FolkProcessingService folkProcessingService, OpusDPSService opusDPSService) {
        this.arrangementsService = arrangementsService;
        this.chantsService = chantsService;
        this.choirsService = choirsService;
        this.folkProcessingService = folkProcessingService;
        this.opusDPSService = opusDPSService;
    }

    public HashMap<String, List<Essay>> getAllEssay(){
        HashMap<String, List<Essay>> essays = new HashMap<>();
        essays.put("arrangement", arrangementsService.findAll());
        essays.put("chants", chantsService.findAll());
        essays.put("chorus", choirsService.findAll());
        essays.put("folkProcessing", folkProcessingService.findAll());
        essays.put("opusDPS", opusDPSService.findAll());
        return essays;
    }
}
