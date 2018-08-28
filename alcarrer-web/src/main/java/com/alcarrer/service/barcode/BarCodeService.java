package com.alcarrer.service.barcode;

import org.springframework.stereotype.Service;

import com.alcarrer.model.Produto;

@Service
public interface BarCodeService {

	Produto encode(String barCode128);

	Produto decode(String barCode128);

}
