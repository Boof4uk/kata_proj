package com.bank.history.mappers;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.models.History;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T19:36:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class HistoryMapperImpl implements HistoryMapper {

    @Override
    public HistoryDTO toDTO(History history) {
        if ( history == null ) {
            return null;
        }

        HistoryDTO historyDTO = new HistoryDTO();

        historyDTO.setTransferAuditId( history.getTransferAuditId() );
        historyDTO.setProfileAuditId( history.getProfileAuditId() );
        historyDTO.setAccountAuditId( history.getAccountAuditId() );
        historyDTO.setAntiFraudAuditId( history.getAntiFraudAuditId() );
        historyDTO.setPublicBankInfoAuditId( history.getPublicBankInfoAuditId() );
        historyDTO.setAuthorizationAuditId( history.getAuthorizationAuditId() );

        return historyDTO;
    }

    @Override
    public History toEntity(HistoryDTO historyDTO) {
        if ( historyDTO == null ) {
            return null;
        }

        History history = new History();

        history.setTransferAuditId( historyDTO.getTransferAuditId() );
        history.setProfileAuditId( historyDTO.getProfileAuditId() );
        history.setAccountAuditId( historyDTO.getAccountAuditId() );
        history.setAntiFraudAuditId( historyDTO.getAntiFraudAuditId() );
        history.setPublicBankInfoAuditId( historyDTO.getPublicBankInfoAuditId() );
        history.setAuthorizationAuditId( historyDTO.getAuthorizationAuditId() );

        return history;
    }

    @Override
    public List<HistoryDTO> toDTOList(List<History> historyList) {
        if ( historyList == null ) {
            return null;
        }

        List<HistoryDTO> list = new ArrayList<HistoryDTO>( historyList.size() );
        for ( History history : historyList ) {
            list.add( toDTO( history ) );
        }

        return list;
    }
}
