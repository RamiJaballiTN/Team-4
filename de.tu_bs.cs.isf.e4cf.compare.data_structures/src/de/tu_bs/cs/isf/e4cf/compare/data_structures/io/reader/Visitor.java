package de.tu_bs.cs.isf.e4cf.compare.data_structures.io.reader;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.impl.NodeImpl;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Node;
import javafx.scene.effect.Lighting;

import com.github.javaparser.ast.visitor.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

import java.util.Arrays;

import com.github.javaparser.ast.*;

/**
 * Part of the custom visitor class. This class extends VoidVisitorAdapter
 * (https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/visitor/VoidVisitorAdapter.html).
 * 
 * @author Serkan Acar
 * @author Pascal Blum
 * @author Paulo Haas
 * @author Hassan Smaoui
 *
 */
public class Visitor extends VoidVisitorAdapter<Node> {
	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/CompilationUnit.html
	 */
	@Override
	public void visit(CompilationUnit n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/MethodDeclaration.html
	 */
	@Override
	public void visit(MethodDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/Modifier.html
	 */
	@Override
	public void visit(Modifier n, Node arg) {
		arg.addAttribute(n.getClass().getSimpleName(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/SimpleName.html
	 */
	@Override
	public void visit(SimpleName n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Name.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/ClassOrInterfaceDeclaration.html
	 */
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Node arg) {
		Node child = new NodeImpl(n.isInterface() ? JavaNodeTypes.Interface.toString() : JavaNodeTypes.Class.toString(),
				arg);
		super.visit(n, child);
		if (n.getExtendedTypes().size() > 0) {
			// Only a single class can be inherited!
			child.addAttribute(JavaNodeTypes.Superclass.name(), n.getExtendedTypes().get(0).getNameAsString());
		}
		int implementedTypeCtr = 0;
		for (ClassOrInterfaceType cit : n.getImplementedTypes()) {
			// Multiple classes can be implemented
			child.addAttribute(JavaNodeTypes.Interface.name() + implementedTypeCtr, cit.getNameAsString());
		}
	}

	/////////////////////////////
	// Serkan

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/type/ClassOrInterfaceType.html
	 */
	@Override
	public void visit(ClassOrInterfaceType n, Node arg) {
		// Do nothing
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/AnnotationDeclaration.html
	 */
	@Override
	public void visit(AnnotationDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/AnnotationMemberDeclaration.html
	 */
	@Override
	public void visit(AnnotationMemberDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ArrayAccessExpr.html
	 */
	@Override
	public void visit(ArrayAccessExpr n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ArrayCreationExpr.html
	 */
	@Override
	public void visit(ArrayCreationExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/ArrayCreationLevel.html
	 */
	@Override
	public void visit(ArrayCreationLevel n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ArrayInitializerExpr.html
	 */
	@Override
	public void visit(ArrayInitializerExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/type/ArrayType.html
	 */
	@Override
	public void visit(ArrayType n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Type.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/AssertStmt.html
	 */
	@Override
	public void visit(AssertStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/AssignExpr.html
	 */
	@Override
	public void visit(AssignExpr n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/BinaryExpr.html
	 */
	@Override
	public void visit(BinaryExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/BlockStmt.html
	 */
	@Override
	public void visit(BlockStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/BooleanLiteralExpr.html
	 */
	@Override
	public void visit(BooleanLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/BreakStmt.html
	 */
	@Override
	public void visit(BreakStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/CastExpr.html
	 */
	@Override
	public void visit(CastExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/CatchClause.html
	 */
	@Override
	public void visit(CatchClause n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/CharLiteralExpr.html
	 */
	@Override
	public void visit(CharLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ClassExpr.html
	 */
	@Override
	public void visit(ClassExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ConditionalExpr.html
	 */
	@Override
	public void visit(ConditionalExpr n, Node arg) {
		Node p = VisitorUtil.Parent(n, arg);
		p.addAttribute(JavaNodeTypes.Condition.name(), n.getCondition().toString());
		p.addAttribute(JavaNodeTypes.Then.name(), n.getThenExpr().toString());
		p.addAttribute(JavaNodeTypes.Else.name(), n.getElseExpr().toString());
	}

	/////////////////////////////

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/PackageDeclaration.html
	 */
	@Override
	public void visit(PackageDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/ImportDeclaration.html
	 */
	@Override
	public void visit(ImportDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}
	
	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/FieldAccessExpr.html
	 */
	@Override
	public void visit(FieldAccessExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Field.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/FieldDeclaration.html
	 */
	@Override
	public void visit(FieldDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg)); // TODO super call?
	}

	//////////////////////////////////////////////////
	// Pascal

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/LambdaExpr.html
	 */
	@Override
	public void visit(LambdaExpr n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/LocalClassDeclarationStmt.html
	 */
	@Override
	public void visit(LocalClassDeclarationStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/LongLiteralExpr.html
	 */
	@Override
	public void visit(LongLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/MarkerAnnotationExpr.html
	 */
	@Override
	public void visit(MarkerAnnotationExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Annotation.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/MemberValuePair.html
	 */
	@Override
	public void visit(MemberValuePair n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Key.toString(), n.getValue().toString());
		c.addAttribute(JavaNodeTypes.Value.toString(), n.getValue().toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/MethodCallExpr.html
	 */
	@Override
	public void visit(MethodCallExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Scope.toString(), n.getScope().toString());
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/MethodReferenceExpr.html
	 */
	@Override
	public void visit(MethodReferenceExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute("Identifier", n.getIdentifier());
		c.addAttribute(JavaNodeTypes.Scope.toString(), n.getScope().toString());
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/Name.html
	 */
	@Override
	public void visit(Name n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Name.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/NameExpr.html
	 */
	@Override
	public void visit(NameExpr n, Node arg) {
		arg.addAttribute("Name", n.getNameAsString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/NormalAnnotationExpr.html
	 */
	@Override
	public void visit(NormalAnnotationExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/NullLiteralExpr.html
	 */
	@Override
	public void visit(NullLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/ObjectCreationExpr.html
	 */
	@Override
	public void visit(ObjectCreationExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Type.toString(), n.getType().toString());
		c.addAttribute(JavaNodeTypes.Scope.toString(), n.getScope().toString());
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/Parameter.html
	 */
	@Override
	public void visit(Parameter n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Type.toString(), n.getTypeAsString());
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/type/PrimitiveType.html
	 */
	@Override
	public void visit(PrimitiveType n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Type.name(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/ReceiverParameter.html
	 */
	@Override
	public void visit(ReceiverParameter n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Type.toString(), n.getType().toString());
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/ReturnStmt.html
	 */
	@Override
	public void visit(ReturnStmt n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		super.visit(n, c);
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/SingleMemberAnnotationExpr.html
	 */
	@Override
	public void visit(SingleMemberAnnotationExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute("Name", n.getNameAsString()); // TODO aufl�sen
		c.addAttribute(JavaNodeTypes.Value.toString(), n.getMemberValue().toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/StringLiteralExpr.html
	 */
	@Override
	public void visit(StringLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/SuperExpr.html
	 */
	@Override
	public void visit(SuperExpr n, Node arg) {
		arg.addAttribute(n.getClass().getSimpleName(), n.toString());
	}

	/////////////////////////////////////////////////////////////////////////
	// Paulo

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/SwitchEntry.html
	 */
	@Override
	public void visit(SwitchEntry n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/SwitchExpr.html
	 */
	@Override
	public void visit(SwitchExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/SwitchStmt.html
	 */
	@Override
	public void visit(SwitchStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/SynchronizedStmt.html
	 */
	@Override
	public void visit(SynchronizedStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/TextBlockLiteralExpr.html
	 */
	@Override
	public void visit(TextBlockLiteralExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/ThisExpr.html
	 */
	@Override
	public void visit(ThisExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/ThrowStmt.html
	 */
	@Override
	public void visit(ThrowStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/TryStmt.html
	 */
	@Override
	public void visit(TryStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/TypeExpr.html
	 */
	@Override
	public void visit(TypeExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/TypeParameter.html
	 */
	@Override
	public void visit(TypeParameter n, Node arg) {
		Node p = VisitorUtil.Parent(n, arg);
		super.visit(n, p);
		for(ClassOrInterfaceType coit : n.findAll(ClassOrInterfaceType.class)) {
			// Does Coit have more attributes than just a name?
			if (coit.getChildNodes().size() > 1) {
				// YES! Visit them.
				Node coitNode = new NodeImpl(JavaNodeTypes.Superclass.name(), p);
				super.visit(coit, coitNode);
			} else {
				// NO! 
				p.addAttribute(JavaNodeTypes.Superclass.name(), coit.asString());
			}
		}
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/UnaryExpr.html
	 */
	@Override
	public void visit(UnaryExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/UnionType.html
	 */
	@Override
	public void visit(UnionType n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/UnknownType.html
	 */
	@Override
	public void visit(UnknownType n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/UnparsableStmt.html
	 */
	@Override
	public void visit(UnparsableStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/VariableDeclarationExpr.html
	 */
	@Override
	public void visit(VariableDeclarationExpr n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/VarType.html
	 */
	@Override
	public void visit(VarType n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/VoidType.html
	 */
	@Override
	public void visit(VoidType n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.toString());
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/WhileStmt.html
	 */
	@Override
	public void visit(WhileStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/type/WildcardType.html
	 */
	@Override
	public void visit(WildcardType n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/YieldStmt.html
	 */
	@Override
	public void visit(YieldStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/////////////////////////
	// Hassan

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/body/ConstructorDeclaration.html
	 * 
	 */
	@Override
	public void visit(ConstructorDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/ContinueStmt.html
	 */
	@Override
	public void visit(ContinueStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/DoStmt.html
	 */
	@Override
	public void visit(DoStmt n, Node arg) {
		VisitorUtil.Leaf(n, VisitorUtil.Parent(n, arg));

	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/stmt/EmptyStmt.html
	 */
	@Override
	public void visit(EmptyStmt n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/DoubleLiteralExpr.html
	 */
	@Override
	public void visit(DoubleLiteralExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);

	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/expr/EnclosedExpr.html
	 */

	@Override
	public void visit(EnclosedExpr n, Node arg) {
		VisitorUtil.Leaf(n, arg);
	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/body/EnumConstantDeclaration.html
	 */

	@Override
	public void visit(EnumConstantDeclaration n, Node arg) {

		super.visit(n, VisitorUtil.Parent(n, arg));

	}

	/**
	 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/com/github/javaparser/ast/body/EnumDeclaration.html
	 */
	@Override
	public void visit(EnumDeclaration n, Node arg) {

		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/ExplicitConstructorInvocationStmt.html
	 */

	@Override
	public void visit(ExplicitConstructorInvocationStmt u, Node n) {
		// TODO

	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/ForEachStmt.html
	 */
	@Override
	public void visit(ForEachStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/ForStmt.html
	 */
	@Override
	public void visit(ForStmt n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/stmt/IfStmt.html
	 */
	@Override
	public void visit(IfStmt n, Node arg) {
		Node p = VisitorUtil.Parent(n, arg);
		super.visit((BinaryExpr) n.getCondition(), p);
		p.addAttribute(JavaNodeTypes.Condition.name(), n.getCondition().toString());
		Node thenNode = new NodeImpl(JavaNodeTypes.Then.name(), p);
		super.visit((BlockStmt) n.getThenStmt(), thenNode);
		if (n.getElseStmt().isPresent()) {
			Node elseNode = new NodeImpl(JavaNodeTypes.Else.name(), p);
			BlockStmt elseStmt = (BlockStmt) n.getElseStmt().get();
			super.visit(elseStmt, elseNode);
		}
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/body/InitializerDeclaration.html
	 */
	@Override
	public void visit(InitializerDeclaration n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/InstanceOfExpr.html/
	 */
	@Override
	public void visit(InstanceOfExpr n, Node arg) {
		Node c = new NodeImpl(n.getClass().getSimpleName(), arg);
		c.addAttribute(JavaNodeTypes.Type.toString(), n.getType().toString());
		c.addAttribute(JavaNodeTypes.Expression.toString(), n.getExpression().toString());
	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/expr/IntegerLiteralExpr.html
	 */
	@Override
	public void visit(IntegerLiteralExpr n, Node arg) {
		arg.addAttribute(JavaNodeTypes.Value.toString(), n.asNumber().toString());

	}

	/**
	 * https://www.javadoc.io/static/com.github.javaparser/javaparser-core/3.17.0/com/github/javaparser/ast/type/IntersectionType.html
	 */
	@Override
	public void visit(IntersectionType n, Node arg) {
		super.visit(n, VisitorUtil.Parent(n, arg));
	}
}