package javacore.filtersortedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Temp {
    public static void main(String args[]) {
        MyApprovalRequest myApprovalRequest = getMyApprovalRequest();

        List<SparePartsOutBoundDto> sparePartsOutBoundDtos = getSparePartsOutBoundDtos();

        Temp temp = new Temp();
        temp.getFilteredList(myApprovalRequest, sparePartsOutBoundDtos);
    }

    private static List<SparePartsOutBoundDto> getSparePartsOutBoundDtos() {
        List<SparePartsOutBoundDto> sparePartsOutBoundDtos = new ArrayList();

        SparePartsOutBoundDto sparePartsOutBoundDto1 = new SparePartsOutBoundDto();
        sparePartsOutBoundDto1.setId(3);
        sparePartsOutBoundDto1.setCostCenter(3);


        SparePartsOutBoundDto sparePartsOutBoundDto5 = new SparePartsOutBoundDto();
        sparePartsOutBoundDto5.setId(12);
        sparePartsOutBoundDto5.setCostCenter(12);


        SparePartsOutBoundDto sparePartsOutBoundDto = new SparePartsOutBoundDto();
        sparePartsOutBoundDto.setId(1);
        sparePartsOutBoundDto.setCostCenter(1);

        SparePartsOutBoundDto sparePartsOutBoundDto2 = new SparePartsOutBoundDto();
        sparePartsOutBoundDto2.setId(7);
        sparePartsOutBoundDto2.setCostCenter(7);

        SparePartsOutBoundDto sparePartsOutBoundDto3 = new SparePartsOutBoundDto();
        sparePartsOutBoundDto3.setId(5);
        sparePartsOutBoundDto3.setCostCenter(5);

        sparePartsOutBoundDtos.add(sparePartsOutBoundDto);
        sparePartsOutBoundDtos.add(sparePartsOutBoundDto1);
        sparePartsOutBoundDtos.add(sparePartsOutBoundDto2);
        sparePartsOutBoundDtos.add(sparePartsOutBoundDto3);
        sparePartsOutBoundDtos.add(sparePartsOutBoundDto5);
        return sparePartsOutBoundDtos;
    }

    private static MyApprovalRequest getMyApprovalRequest() {
        MyApprovalRequest myApprovalRequest = new MyApprovalRequest();
        myApprovalRequest.setOffset(3);
        myApprovalRequest.setLimit(2);
        return myApprovalRequest;
    }

    private void getFilteredList(MyApprovalRequest r, List<SparePartsOutBoundDto> sparePartsOutBoundDtos) {
//            , PageableResponse<SparePartsOutBoundDto> data) {
        Collections.sort(sparePartsOutBoundDtos, new Comparator<SparePartsOutBoundDto>() {
            @Override
            public int compare(SparePartsOutBoundDto o1, SparePartsOutBoundDto o2) {
                return o1.getId().compareTo(o2.getId());//升序
            }
        });
        Integer offset = r.getOffset();
        Integer limit = r.getLimit();
        Integer length = offset + limit;
        List<SparePartsOutBoundDto> spoutboundDto = sparePartsOutBoundDtos.stream().filter(x -> {
            int index = sparePartsOutBoundDtos.indexOf(x);
            if (index <= length && index >= offset) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
//        data.setItems(spoutboundDto);
    }
}



