package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.types.PhpNull;

import java.util.List;

/**
 * Represents a group of expressions which are executed in sequence
 * and the last expression is returned.

 * This is used in for blocks for initializers, updater, and conditions
 *
 * @author abertschi
 */
public final class ExprGroupNode extends PhpExprNode {

    @Children
    private PhpExprNode[] expressionNodes;

    public ExprGroupNode(PhpExprNode... nodes) {
        this.expressionNodes = nodes;
    }

    public ExprGroupNode(List<PhpExprNode> nodes) {
        this(nodes.toArray(new PhpExprNode[nodes.size()]));
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame) {
        CompilerAsserts.partialEvaluationConstant(expressionNodes.length);
        if (expressionNodes.length == 0) {
            return PhpNull.SINGLETON;
        }
        for (int i = 0; i < expressionNodes.length - 1; i++) {
            expressionNodes[i].executeGeneric(frame);
        }
        return expressionNodes[expressionNodes.length - 1].executeGeneric(frame);
    }
}
