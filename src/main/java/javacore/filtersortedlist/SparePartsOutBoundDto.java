package javacore.filtersortedlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SparePartsOutBoundDto { // extends BaseDto

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("outbound_no")
    private String outboundNo;

    /**
     * 备件ID
     */
    @JsonProperty("parts_id")
    private Integer partsId;


    /**
     * 备件NO-新增
     */
//    @TableField(exist=false)
    @JsonProperty("parts_no")
    private String partsNo;

    @JsonProperty("specification")
    private String specification;
    /**
     * 领用人
     */
    @JsonProperty("outbound_by")
    private Integer outboundBy;

    @JsonProperty("outbound_user_name")
    private String outboundUserName;

    /**
     * 用途（1：维修，2：保养，3：其他）
     */
    @JsonProperty("purpose")
    private Integer purpose;

    /**
     * 成本中心
     */
    @JsonProperty("cost_center")
    private Integer costCenter;

    @JsonProperty("cost_center_name")
    private String costCenterName;
    /**
     * 领用部门ID
     */
    @JsonProperty("dept_id")
    private Integer deptId;

    /**
     * 领用部门名
     */
    @JsonProperty("dept_name")
    private String deptName;

    /**
     * 状态
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 出库数量
     */
    @JsonProperty("out_amount")
    private BigDecimal outAmount;

    /**
     * 当前审批人
     */
    @JsonProperty("current_approver")
    private Integer currentApprover;

    /**
     * 备注
     */
    @JsonProperty("remark")
    private String remark;

    @JsonProperty("parts_name")
    private String partsName;

    @JsonProperty("type_id")
    private Integer typeId;

    @JsonProperty("unit_id")
    private Integer unitId;

    @JsonProperty("inventory")
    private BigDecimal inventory;

    @JsonProperty("inventory_alert")
    private BigDecimal inventoryAlert;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("unit_name")
    private String unitName;

    @JsonProperty("equipment_id")
    private String equipmentId;

//    /// 审批历程
//    @JsonProperty("approval_progress_list")
//    List<ApprovalProgressDto> approvalProgressList;
//
//    @JsonProperty("equipments")
//    private List<SparePartsEquipmentDTO> equipments;
//
//    /// 领用数量的组成
//    @JsonProperty("outbound_list")
//    private List<EtpmSparePartsOutboundList> outboundList;
}
