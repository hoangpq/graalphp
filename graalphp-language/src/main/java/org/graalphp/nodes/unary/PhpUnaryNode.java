package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(description = "abstract class for all unary expressions")
@NodeChild(value = "valueNode", type = PhpExprNode.class)
public abstract class PhpUnaryNode extends PhpExprNode {

    protected abstract PhpExprNode getValueNode();
}
