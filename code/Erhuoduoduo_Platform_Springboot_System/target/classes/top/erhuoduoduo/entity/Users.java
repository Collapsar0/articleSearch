package top.erhuoduoduo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Collapsar
 * @since 2021-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
@ApiModel(value="Users对象", description="")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID",example = "0")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户账号")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty(value = "密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "昵称")
    @TableField("user_nickname")
    private String userNickname;

    @ApiModelProperty(value = "头像")
    @TableField("user_picture")
    private String userPicture;

    @ApiModelProperty(value = "手机号码")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "状态")
    @TableField("user_state")
    private String userState;

    @ApiModelProperty(value = "被举报次数",example = "0")
    @TableField("report_cnt")
    private Integer reportCnt;

    @ApiModelProperty(value = "用户类别")
    @TableField("user_category")
    private String userCategory;

    @ApiModelProperty(value = "性别（0-女,1-男）",example = "0")
    @TableField("user_sex")
    private Integer userSex;

    @ApiModelProperty(value = "入学时间",example = "0")
    @TableField("user_admission_time")
    private Integer userAdmissionTime;

    @ApiModelProperty(value = "专业")
    @TableField("user_major")
    private String userMajor;

    @ApiModelProperty(value = "用户评价",example = "0")
    @TableField("user_comment")
    private Double userComment;


}
