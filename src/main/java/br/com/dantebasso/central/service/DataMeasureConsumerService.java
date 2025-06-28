package br.com.dantebasso.central.service;

import br.com.dantebasso.central.model.DataMeasure;

public interface DataMeasureConsumerService {
    
    void consume(DataMeasure dataMeasure);
    
}
